package org.unibl.etf.ip.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.model.SavjetnikPorukaEntity;
import org.unibl.etf.ip.backend.repository.ConsultantMessageRepository;

@Service
public class ConsultantMessageService {

    private Logger logger = LoggerFactory.getLogger(ConsultantMessageService.class);

    @Autowired
    private ConsultantMessageRepository repository;

    public SavjetnikPorukaEntity messageConsultant(SavjetnikPorukaEntity message) {
        logger.info("User with id " + message.getKorisnikId() + " messaged consultants!");
        return repository.save(message);

    }

}
