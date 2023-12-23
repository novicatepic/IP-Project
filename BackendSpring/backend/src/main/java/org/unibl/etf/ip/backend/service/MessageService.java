package org.unibl.etf.ip.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.model.SavjetnikPorukaEntity;
import org.unibl.etf.ip.backend.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository repository;

    public SavjetnikPorukaEntity messageConsultant(SavjetnikPorukaEntity message) {

        return  repository.save(message);

    }

}
