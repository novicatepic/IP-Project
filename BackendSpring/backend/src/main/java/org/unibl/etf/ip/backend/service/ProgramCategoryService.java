package org.unibl.etf.ip.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.model.KategorijaProgramEntity;
import org.unibl.etf.ip.backend.repository.ProgramCategoryRepository;

@Service
public class ProgramCategoryService {

    @Autowired
    private ProgramCategoryRepository repository;

    public KategorijaProgramEntity addCategoryToProgram(Integer categoryId, Integer programId) {
        KategorijaProgramEntity kategorijaProgramEntity = new KategorijaProgramEntity();
        kategorijaProgramEntity.setProgramId(programId);
        kategorijaProgramEntity.setKategorijaId(categoryId);
        return repository.save(kategorijaProgramEntity);
    }

}
