package org.unibl.etf.ip.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.ip.backend.errorservice.ForbiddenEntity;
import org.unibl.etf.ip.backend.exceptions.NotFoundException;
import org.unibl.etf.ip.backend.loginservice.UserLoginHelp;
import org.unibl.etf.ip.backend.model.KategorijaEntity;
import org.unibl.etf.ip.backend.model.KorisnikPretplacenKategorijaEntity;
import org.unibl.etf.ip.backend.model.MessageModel;
import org.unibl.etf.ip.backend.service.CategorySubscribeService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/category-subscriptions")
public class CategorySubscribeController {

    @Autowired
    private CategorySubscribeService service;

    @GetMapping("/unsubscribed/{userid}")
    public ResponseEntity<List<KategorijaEntity>> unsubscribedCategories(@PathVariable("userid") Integer userId) {

        if(!UserLoginHelp.checkUserValidity(userId)) {
            return ForbiddenEntity.returnForbidden();
        }

        return new ResponseEntity<>( service.getUnsubscribedCategories(userId), HttpStatus.OK);
    }

    @GetMapping("/subscribed/{userid}")
    public ResponseEntity<List<KategorijaEntity>> subscribedCategories(@PathVariable("userid") Integer userId) {

        if(!UserLoginHelp.checkUserValidity(userId)) {
            return ForbiddenEntity.returnForbidden();
        }

        return new ResponseEntity<>( service.getSubscribedCategories(userId), HttpStatus.OK);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<KorisnikPretplacenKategorijaEntity> subscribeToCategory(@RequestBody KorisnikPretplacenKategorijaEntity subscription) {
        Integer userId = subscription.getKorisnikId();

        if(!UserLoginHelp.checkUserValidity(userId)) {
            return ForbiddenEntity.returnForbidden();
        }

        return new ResponseEntity<>( service.subscribe(subscription), HttpStatus.OK);
    }

    @DeleteMapping("/unsubscribe/{userId}/{categoryId}")
    public ResponseEntity<MessageModel> unsubscribeFromCategory
            (@PathVariable("userId")Integer userId, @PathVariable("categoryId")Integer categoryId) throws NotFoundException {

        if(!UserLoginHelp.checkUserValidity(userId)) {
            return ForbiddenEntity.returnForbidden();
        }

        KorisnikPretplacenKategorijaEntity kat = new KorisnikPretplacenKategorijaEntity();
        kat.setKategorijaId(categoryId);
        kat.setKorisnikId(userId);
        //return new ResponseEntity<>( service.subscribe(subscription), HttpStatus.OK);
        if(service.unsubscribe(kat)) {
            MessageModel m = new MessageModel();
            m.setText("Success");
            return new ResponseEntity<>(m, HttpStatus.OK);
        }
        MessageModel m = new MessageModel();
        m.setText("Failure");
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

}
