package org.unibl.etf.ip.backend.controller;

import org.javalite.http.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.ip.backend.exceptions.NotFoundException;
import org.unibl.etf.ip.backend.model.KategorijaEntity;
import org.unibl.etf.ip.backend.model.KategorijaProgramEntity;
import org.unibl.etf.ip.backend.model.KorisnikPretplacenKategorijaEntity;
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
        //System.out.println("IN 1");
        return new ResponseEntity<>( service.getUnsubscribedCategories(userId), HttpStatus.OK);
    }

    @GetMapping("/subscribed/{userid}")
    public ResponseEntity<List<KategorijaEntity>> subscribedCategories(@PathVariable("userid") Integer userId) {
        //System.out.println("IN 1");
        return new ResponseEntity<>( service.getSubscribedCategories(userId), HttpStatus.OK);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<KorisnikPretplacenKategorijaEntity> subscribeToCategory(@RequestBody KorisnikPretplacenKategorijaEntity subscription) {
        return new ResponseEntity<>( service.subscribe(subscription), HttpStatus.OK);
    }

    @DeleteMapping("/unsubscribe/{userId}/{categoryId}")
    public ResponseEntity<String> unsubscribeFromCategory
            (@PathVariable("userId")Integer userId, @PathVariable("categoryId")Integer categoryId) throws NotFoundException {
        KorisnikPretplacenKategorijaEntity kat = new KorisnikPretplacenKategorijaEntity();
        kat.setKategorijaId(categoryId);
        kat.setKorisnikId(userId);
        //return new ResponseEntity<>( service.subscribe(subscription), HttpStatus.OK);
        if(service.unsubscribe(kat)) {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failure", HttpStatus.OK);
    }

}
