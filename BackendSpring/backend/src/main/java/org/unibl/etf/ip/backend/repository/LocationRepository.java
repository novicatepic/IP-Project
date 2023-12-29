package org.unibl.etf.ip.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etf.ip.backend.model.LokacijaEntity;

@Repository
public interface LocationRepository extends JpaRepository<LokacijaEntity, Integer> {
}
