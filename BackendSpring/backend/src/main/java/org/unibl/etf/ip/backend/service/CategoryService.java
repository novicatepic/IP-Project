package org.unibl.etf.ip.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.model.KategorijaEntity;
import org.unibl.etf.ip.backend.model.KategorijaProgramEntity;
import org.unibl.etf.ip.backend.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<KategorijaEntity> getAllCategories() {
        return repository.findAll();
    }

}
