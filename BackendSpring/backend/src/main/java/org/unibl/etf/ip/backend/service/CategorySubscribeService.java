package org.unibl.etf.ip.backend.service;

import jakarta.transaction.Transactional;
import org.apache.catalina.LifecycleState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(CategorySubscribeService.class);

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
            if(!found && !category.getTerminirana()) {
                result.add(category);
            }
        }
        logger.info("User with id " + userId + " checked his unsubscribed categories");
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
            if(found && !category.getTerminirana()) {
                result.add(category);
            }
        }
        logger.info("User with id " + userId + " checked his subscribed categories");
        return result;

    }

    public KorisnikPretplacenKategorijaEntity subscribe(KorisnikPretplacenKategorijaEntity subscription) {
        logger.info("Subscription to category " + subscription.getKategorijaId() + " by user " + subscription.getKorisnikId());
        return repository.save(subscription);
    }

    @Transactional
    public boolean unsubscribe(KorisnikPretplacenKategorijaEntity subscription) throws NotFoundException {
        List<KorisnikPretplacenKategorijaEntity> entities = repository.findAll();
        for(KorisnikPretplacenKategorijaEntity entity : entities) {
            if(entity.getKorisnikId() == subscription.getKorisnikId() && entity.getKategorijaId() == subscription.getKategorijaId()) {
                logger.info("Unsubscription from category " + subscription.getKategorijaId() + " by user " + subscription.getKorisnikId());
                repository.delete(subscription);
                return true;
            }
        }
        throw new NotFoundException();
    }

}
