package org.unibl.etf.ip.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.ip.backend.model.KategorijaProgramEntity;
import org.unibl.etf.ip.backend.service.ProgramCategoryService;

@RestController
@RequestMapping("/program-category")
public class ProgramCategoryController {

    @Autowired
    private ProgramCategoryService service;

    @PostMapping("/{categoryId}/{programId}")
    public ResponseEntity<KategorijaProgramEntity> addCategoryToProgram(@PathVariable("categoryId")Integer categoryId,
                                                                        @PathVariable("programId")Integer programId) {
        return new ResponseEntity<>(service.addCategoryToProgram(categoryId, programId), HttpStatus.OK);
    }

}
