package org.unibl.etf.ip.backend.service;

import jakarta.transaction.Transactional;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.exceptions.NotFoundException;
import org.unibl.etf.ip.backend.model.KategorijaEntity;
import org.unibl.etf.ip.backend.model.KorisnikPretplacenKategorijaEntity;
import org.unibl.etf.ip.backend.model.KorisnikPretplacenKategorijaEntityPK;
import org.unibl.etf.ip.backend.repository.CategoryRepository;
import org.unibl.etf.ip.backend.repository.CategorySubscribeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategorySubscribeService {

    @Autowired
    CategorySubscribeRepository repository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<KategorijaEntity> getUnsubscribedCategories(Integer userId) {
        List<KategorijaEntity> result = new ArrayList<>();

        List<KorisnikPretplacenKategorijaEntity> currentSubscriptions = repository.findByKorisnikId(userId);
        List<KategorijaEntity> allCategories = categoryRepository.findAll();

        //System.out.println("IN 2");

        for(KategorijaEntity category : allCategories) {
            boolean found = false;
            for(KorisnikPretplacenKategorijaEntity subscription : currentSubscriptions) {
                if(category.getId() == subscription.getKategorijaId()) {
                    found = true;
                }
            }
            if(!found) {
                result.add(category);
            }
        }

        return result;

    }

    public List<KategorijaEntity> getSubscribedCategories(Integer userId) {
        List<KategorijaEntity> result = new ArrayList<>();

        List<KorisnikPretplacenKategorijaEntity> currentSubscriptions = repository.findByKorisnikId(userId);
        List<KategorijaEntity> allCategories = categoryRepository.findAll();

        //System.out.println("IN 2");

        for(KategorijaEntity category : allCategories) {
            boolean found = false;
            for(KorisnikPretplacenKategorijaEntity subscription : currentSubscriptions) {
                if(category.getId() == subscription.getKategorijaId()) {
                    found = true;
                }
            }
            if(found) {
                result.add(category);
            }
        }

        return result;

    }

    public KorisnikPretplacenKategorijaEntity subscribe(KorisnikPretplacenKategorijaEntity subscription) {
        return repository.save(subscription);
    }

    @Transactional
    public boolean unsubscribe(KorisnikPretplacenKategorijaEntity subscription) throws NotFoundException {
        List<KorisnikPretplacenKategorijaEntity> entities = repository.findAll();
        for(KorisnikPretplacenKategorijaEntity entity : entities) {
            if(entity.getKorisnikId() == subscription.getKorisnikId() && entity.getKategorijaId() == subscription.getKategorijaId()) {
                repository.delete(subscription);
                return true;
            }
        }
        throw new NotFoundException();
    }

}
