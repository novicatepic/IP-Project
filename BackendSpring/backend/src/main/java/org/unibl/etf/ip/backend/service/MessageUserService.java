package org.unibl.etf.ip.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.model.PorukaEntity;
import org.unibl.etf.ip.backend.repository.MessageUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageUserService {

    @Autowired
    private MessageUserRepository repository;

    public PorukaEntity createUserMessage(PorukaEntity message) {
        return repository.save(message);
    }

    public List<PorukaEntity> readMessages(Integer id) {
        List<PorukaEntity> messages = repository.findAll();
        List<PorukaEntity> result = new ArrayList<>();
        for(PorukaEntity m : messages) {
            if(m.getPrimalac().getId() == id) {
                result.add(m);
            }
        }
        return result;
    }

    public List<PorukaEntity> readUnreadMessages(Integer id) {
        List<PorukaEntity> messages = repository.findAll();
        List<PorukaEntity> result = new ArrayList<>();
        for(PorukaEntity m : messages) {
            if(m.getPrimalac().getId() == id && !m.getProcitana()) {
                result.add(m);
            }
        }
        return result;
    }

}
