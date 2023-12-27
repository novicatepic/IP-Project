package org.unibl.etf.ip.backend.controller;

import com.rometools.rome.io.FeedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.ip.backend.model.RSSModel;
import org.unibl.etf.ip.backend.service.RSSService;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rss")
public class RSSController {

    @Autowired
    private RSSService service;

    @GetMapping
    public ResponseEntity<List<RSSModel>> getRSSFeed() throws FeedException, IOException {
        return new ResponseEntity<>(service.fetchRssFeed(), HttpStatus.OK);
    }

}
