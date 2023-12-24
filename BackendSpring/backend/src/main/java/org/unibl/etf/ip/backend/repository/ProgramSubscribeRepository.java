package org.unibl.etf.ip.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etf.ip.backend.model.KorisnikPretplacenProgramEntity;
import org.unibl.etf.ip.backend.model.KorisnikPretplacenProgramEntityPK;

@Repository
public interface ProgramSubscribeRepository extends JpaRepository<KorisnikPretplacenProgramEntity, KorisnikPretplacenProgramEntityPK> {



}
