package org.unibl.etf.ip.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;

public class KorisnikPretplacenKategorijaEntityPK implements Serializable {
    @Column(name = "korisnik_id", nullable = false)
    @Id
    private Integer korisnikId;

    @Column(name = "kategorija_id", nullable = false)
    @Id
    private Integer kategorijaId;

    public Integer getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Integer getKategorijaId() {
        return kategorijaId;
    }

    public void setKategorijaId(Integer kategorijaId) {
        this.kategorijaId = kategorijaId;
    }
}
