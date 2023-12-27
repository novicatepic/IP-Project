package org.unibl.etf.ip.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etf.ip.backend.model.KorisnikPretplacenKategorijaEntity;
import org.unibl.etf.ip.backend.model.KorisnikPretplacenKategorijaEntityPK;

import java.util.List;

@Repository
public interface CategorySubscribeRepository extends JpaRepository<KorisnikPretplacenKategorijaEntity, KorisnikPretplacenKategorijaEntityPK> {

    public List<KorisnikPretplacenKategorijaEntity> findByKorisnikId(Integer korisnikId);

}
