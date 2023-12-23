package org.unibl.etf.ip.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.model.PitanjeEntity;
import org.unibl.etf.ip.backend.model.ProgramEntity;
import org.unibl.etf.ip.backend.repository.FitnessProgramRepository;
import org.unibl.etf.ip.backend.repository.QuestionRepository;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository repository;

    public List<PitanjeEntity> getProgramById(Integer id) throws Exception {
        return repository.findByProgram_Id(id);
    }

}
