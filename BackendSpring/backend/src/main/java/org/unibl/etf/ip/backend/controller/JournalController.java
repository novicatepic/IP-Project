package org.unibl.etf.ip.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.ip.backend.errorservice.ForbiddenEntity;
import org.unibl.etf.ip.backend.exceptions.MethodNotAllowedException;
import org.unibl.etf.ip.backend.exceptions.NotFoundException;
import org.unibl.etf.ip.backend.loginservice.UserLoginHelp;
import org.unibl.etf.ip.backend.model.DnevnikUnosEntity;
import org.unibl.etf.ip.backend.model.MessageModel;
import org.unibl.etf.ip.backend.service.JournalService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/journals")
public class JournalController {

    @Autowired
    private JournalService service;


    @GetMapping("/journal-entry/{id}")
    public ResponseEntity<List<DnevnikUnosEntity>> getJournalEntries(@PathVariable("id") Integer id) {
        if(!UserLoginHelp.checkUserValidity(id)) {
            return ForbiddenEntity.returnForbidden();
        }

       return new ResponseEntity<>(service.getJournalEntries(id), HttpStatus.OK);
    }

    @PostMapping("/journal-entry")
    public ResponseEntity<DnevnikUnosEntity> createJournalEntry(@RequestBody DnevnikUnosEntity journalEntry) {
        if(!UserLoginHelp.checkUserValidity(journalEntry.getDnevnikKorisnikId())) {
            return ForbiddenEntity.returnForbidden();
        }
        return new ResponseEntity<>(service.createJournalEntry(journalEntry), HttpStatus.OK);
    }

    @DeleteMapping("/journal-entry/{id}/{userId}")
    public ResponseEntity<MessageModel> deleteJournalEntry(@PathVariable("id") Integer id,
                                                           @PathVariable("userId") Integer userId)
                                                            throws NotFoundException, MethodNotAllowedException {
        service.deleteJournalEntry(id, userId);
        MessageModel m = new MessageModel("Successfully deleted journal entry");
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @GetMapping("/download-journal/{id}")
    public ResponseEntity<byte[]> downloadPDF(@PathVariable("id") Integer id) throws Exception {
        if(!UserLoginHelp.checkUserValidity(id)) {
            return ForbiddenEntity.returnForbidden();
        }

        byte[] baos = service.makePDF(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "document.pdf");

        return new ResponseEntity<>(baos, headers, HttpStatus.OK);

    }

}
