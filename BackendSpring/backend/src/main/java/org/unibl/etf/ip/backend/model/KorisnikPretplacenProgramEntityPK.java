package org.unibl.etf.ip.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class KorisnikPretplacenProgramEntityPK implements Serializable {
    @Column(name = "korisnik_id", nullable = false)
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer korisnikId;
    @Column(name = "program_id", nullable = false)
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer programId;

    public Integer getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

}
