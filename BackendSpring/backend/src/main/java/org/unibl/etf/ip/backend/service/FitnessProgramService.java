package org.unibl.etf.ip.backend.service;

import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public ProgramEntity createProgram(ProgramEntity fitnessProgram) throws Exception {
        return repository.save(fitnessProgram);
    }

    public void deleteProgram(Integer id) {
        repository.deleteById(id);
    }

    public KorisnikPretplacenProgramEntity subscribeToAProgram(KorisnikPretplacenProgramEntity entity) {
        List<KorisnikPretplacenProgramEntity> allEntitites = subscribeRepository.findAll();
        //already subscribed
        for(KorisnikPretplacenProgramEntity k : allEntitites) {
            if(k.getKorisnikId() == entity.getKorisnikId() && k.getProgramId() == entity.getProgramId()) {
                //or throw exception -> todo
                return null;
            }
        }

        //update fitness program if it wasn't active -> now it has participants
        Optional<ProgramEntity> pe = repository.findById(entity.getProgramId());
        if(pe.isPresent()) {
            ProgramEntity fitnessProgram = pe.get();
            if(!fitnessProgram.getUcestvovan()) {
                fitnessProgram.setUcestvovan(true);
                repository.save(fitnessProgram);
            }
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
                result.add(k.getFitnessProgram());
            }
        }
        return result;
    }

    public List<ProgramEntity> getUserUnparticipations(Integer userId) {
        List<KorisnikPretplacenProgramEntity> allEntitites = subscribeRepository.findAll();
        List<ProgramEntity> result = new ArrayList<>();
        for(KorisnikPretplacenProgramEntity k : allEntitites) {
            if(k.getKorisnikId() != userId && !k.getFitnessProgram().getAktivan()) {
                result.add(k.getFitnessProgram());
            }
        }
        return result;
    }

}
