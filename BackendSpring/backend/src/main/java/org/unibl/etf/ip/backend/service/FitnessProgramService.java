package org.unibl.etf.ip.backend.service;

import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.exceptions.MethodNotAllowedException;
import org.unibl.etf.ip.backend.exceptions.NotEnoughMoneyException;
import org.unibl.etf.ip.backend.exceptions.NotFoundException;
import org.unibl.etf.ip.backend.model.KorisnikPretplacenProgramEntity;
import org.unibl.etf.ip.backend.model.ProgramEntity;
import org.unibl.etf.ip.backend.repository.FitnessProgramRepository;
import org.unibl.etf.ip.backend.repository.ProgramSubscribeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FitnessProgramService {

    @Autowired
    private FitnessProgramRepository repository;

    @Autowired
    private ProgramSubscribeRepository subscribeRepository;

    public List<ProgramEntity> getPrograms() {
        return repository.findAll();
    }

    public List<ProgramEntity> getMyPrograms(Integer id) {
        return repository.findByKreator_Id(id);
    }

    public ProgramEntity getProgramById(Integer id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public ProgramEntity createProgram(ProgramEntity fitnessProgram) {
        return repository.save(fitnessProgram);
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
            if(k.getKorisnikId() == userId && k.getFitnessProgram().getAktivan()) {
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
                System.out.println("IN");
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
                System.out.println("1="+k.getFitnessProgram().getNaziv());
                results.add(k.getFitnessProgram());
            }
        }

        List<ProgramEntity> allPrograms = repository.findAll();
        List<ProgramEntity> toReturn = new ArrayList<>();
        for(ProgramEntity program : allPrograms) {
            boolean participated = false;
            for(ProgramEntity result : results) {
                if(program.getId() == result.getId()) {
                    System.out.println("2="+true);
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
