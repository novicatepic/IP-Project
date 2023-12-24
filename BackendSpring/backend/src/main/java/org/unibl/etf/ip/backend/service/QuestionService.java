package org.unibl.etf.ip.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.model.PitanjeEntity;
import org.unibl.etf.ip.backend.model.SavjetnikPorukaEntity;
import org.unibl.etf.ip.backend.repository.ConsultantQuestionRepository;
import org.unibl.etf.ip.backend.repository.QuestionRepository;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository repository;

    @Autowired
    private ConsultantQuestionRepository consultantQuestionRepository;

    public List<PitanjeEntity> getProgramById(Integer id)  {
        return repository.findByProgram_Id(id);
    }

    public PitanjeEntity createQuestion(PitanjeEntity question) {
        return repository.save(question);
    }

    public PitanjeEntity respondToAQuestion(PitanjeEntity question) {
        return repository.save(question);
    }


    public SavjetnikPorukaEntity createQuestionForConsultant(SavjetnikPorukaEntity question) {
        return consultantQuestionRepository.save(question);
    }

}
