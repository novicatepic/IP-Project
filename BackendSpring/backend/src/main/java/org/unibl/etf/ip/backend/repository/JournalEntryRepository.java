package org.unibl.etf.ip.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etf.ip.backend.model.DnevnikUnosEntity;

@Repository
public interface JournalEntryRepository extends JpaRepository<DnevnikUnosEntity, Integer> {



}
