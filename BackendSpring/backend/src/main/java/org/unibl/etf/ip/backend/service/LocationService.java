package org.unibl.etf.ip.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.model.LokacijaEntity;
import org.unibl.etf.ip.backend.repository.LocationRepository;

@Service
public class LocationService {

    @Autowired
    private LocationRepository repository;

    public LokacijaEntity insertLocation(LokacijaEntity entity) {
        return this.repository.save(entity);
    }

}
