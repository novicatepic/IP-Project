package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pitanje", schema = "ip_project", catalog = "")
public class PitanjeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "tekst", nullable = false, length = 1000)
    private String tekst;

    @Basic
    @Column(name = "program_id", nullable = false)
    private Integer programId;
    @Basic
    @Column(name = "korisnik_id", nullable = false)
    private Integer korisnikId;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "korisnik_id", insertable = false, updatable = false)
    private KorisnikEntity user;

    @OneToMany(mappedBy = "pitanjeId", cascade = CascadeType.ALL)
    private List<OdgovorEntity> odgovori = new ArrayList<>();

    public KorisnikEntity getUser() {
        return user;
    }

    public void setUser(KorisnikEntity user) {
        this.user = user;
    }

    public List<OdgovorEntity> getOdgovori() {
        return odgovori;
    }

    public void setOdgovori(List<OdgovorEntity> odgovori) {
        this.odgovori = odgovori;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Integer getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }
}
