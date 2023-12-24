package org.unibl.etf.ip.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.model.KorisnikPretplacenProgramEntity;
import org.unibl.etf.ip.backend.model.ProgramEntity;
import org.unibl.etf.ip.backend.repository.FitnessProgramRepository;
import org.unibl.etf.ip.backend.repository.ProgramSubscribeRepository;

import java.util.List;

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

    public ProgramEntity getProgramById(Integer id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("Error: Entity not found with ID " + id));
    }

    public ProgramEntity createProgram(ProgramEntity fitnessProgram) throws Exception {
        return repository.save(fitnessProgram);
    }

    public void deleteProgram(Integer id) {
        repository.deleteById(id);
    }

    public KorisnikPretplacenProgramEntity subscribeToAProgram(KorisnikPretplacenProgramEntity entity) {
        return subscribeRepository.save(entity);
    }

}
