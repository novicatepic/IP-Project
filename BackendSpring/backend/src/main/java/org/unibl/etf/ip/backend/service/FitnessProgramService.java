package org.unibl.etf.ip.backend.service;

import org.hibernate.annotations.NotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.controller.FitnessProgramController;
import org.unibl.etf.ip.backend.exceptions.MethodNotAllowedException;
import org.unibl.etf.ip.backend.exceptions.NotEnoughMoneyException;
import org.unibl.etf.ip.backend.exceptions.NotFoundException;
import org.unibl.etf.ip.backend.model.KorisnikPretplacenProgramEntity;
import org.unibl.etf.ip.backend.model.ProgramEntity;
import org.unibl.etf.ip.backend.repository.FitnessProgramRepository;
import org.unibl.etf.ip.backend.repository.ProgramSubscribeRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FitnessProgramService {

    private Logger logger = LoggerFactory.getLogger(FitnessProgramService.class);

    @Autowired
    private FitnessProgramRepository repository;

    @Autowired
    private ProgramSubscribeRepository subscribeRepository;

    public List<ProgramEntity> getPrograms() {
        return repository.findAll();
    }

    public List<ProgramEntity> getProgramsFromLastDay(int categoryId) {
        List<ProgramEntity> allPrograms = repository.findAll();

        // Get the current date
        Date currentDate = new Date();

        // Use Calendar to subtract seven days from the current date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date sevenDaysAgo = calendar.getTime();

        // Filter programs created in the last seven days
        return allPrograms.stream()
                .filter(program -> program.getDatumKreiranja().after(sevenDaysAgo) && program.getKategorijaId() == categoryId)
                .collect(Collectors.toList());
    }

    public List<ProgramEntity> getMyPrograms(Integer id) {
        return repository.findByKreator_Id(id);
    }

    public ProgramEntity getProgramById(Integer id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public ProgramEntity createProgram(ProgramEntity fitnessProgram) {
        ProgramEntity program = repository.save(fitnessProgram);
        logger.info("User with id " + fitnessProgram.getKreatorId() + " created program with id " + program.getId());
        return program;
    }

    public void deleteProgram(Integer id, Integer userId) throws NotFoundException, MethodNotAllowedException {
        ProgramEntity program = repository.findById(id).orElseThrow(NotFoundException::new);

        if(program.getKreatorId() != userId) {
            throw new MethodNotAllowedException();
        }

        repository.deleteById(id);
    }

    public KorisnikPretplacenProgramEntity subscribeToAProgram(KorisnikPretplacenProgramEntity entity)
            throws NotFoundException, NotEnoughMoneyException {
        List<KorisnikPretplacenProgramEntity> allEntitites = subscribeRepository.findAll();
        //already subscribed
        for(KorisnikPretplacenProgramEntity k : allEntitites) {
            if(k.getKorisnikId() == entity.getKorisnikId() && k.getProgramId() == entity.getProgramId()) {
                throw new NotFoundException();
            }
        }

        //update fitness program if it wasn't active -> now it has participants
        Optional<ProgramEntity> pe = repository.findById(entity.getProgramId());
        if(pe.isPresent()) {

            ProgramEntity fitnessProgram = pe.get();

            if(fitnessProgram.getCijena() > entity.getVrijednost()) {
                throw new NotEnoughMoneyException();
            }

            if(!fitnessProgram.getUcestvovan()) {
                fitnessProgram.setUcestvovan(true);
                repository.save(fitnessProgram);
            }
        } else {
            throw new NotFoundException();
        }

        return subscribeRepository.save(entity);
    }

    public List<ProgramEntity> getUserParticipations(Integer userId) {
        List<KorisnikPretplacenProgramEntity> allEntitites = subscribeRepository.findAll();
        List<ProgramEntity> result = new ArrayList<>();
        for(KorisnikPretplacenProgramEntity k : allEntitites) {
            if(k.getKorisnikId() == userId && (k.getFitnessProgram().getAktivan())) {
                result.add(k.getFitnessProgram());
            }
        }
        return result;
    }

    public List<ProgramEntity> getPastUserParticipations(Integer userId) {
        List<KorisnikPretplacenProgramEntity> allEntitites = subscribeRepository.findAll();
        List<ProgramEntity> result = new ArrayList<>();
        for(KorisnikPretplacenProgramEntity k : allEntitites) {
            if(k.getKorisnikId() == userId && !k.getFitnessProgram().getAktivan()) {
                result.add(k.getFitnessProgram());
            }
        }
        return result;
    }

    public List<ProgramEntity> getUserUnparticipations(Integer userId) {
        List<KorisnikPretplacenProgramEntity> allEntitites = subscribeRepository.findAll();
        List<ProgramEntity> results = new ArrayList<>();
        for(KorisnikPretplacenProgramEntity k : allEntitites) {
            if(k.getKorisnikId() == userId) {
                results.add(k.getFitnessProgram());
            }
        }

        List<ProgramEntity> allPrograms = repository.findAll();
        List<ProgramEntity> toReturn = new ArrayList<>();
        for(ProgramEntity program : allPrograms) {
            boolean participated = false;
            for(ProgramEntity result : results) {
                if(program.getId() == result.getId()) {
                    participated = true;
                }
            }
            if(!participated && program.getAktivan()) {
                toReturn.add(program);
            }
        }

        return toReturn;
    }

}
