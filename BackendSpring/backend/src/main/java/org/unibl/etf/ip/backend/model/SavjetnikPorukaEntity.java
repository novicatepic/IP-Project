package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "savjetnik_poruka", schema = "ip_project", catalog = "")
public class SavjetnikPorukaEntity {
    @Basic
    @Column(name = "tekst", nullable = false, length = 1000)
    private String tekst;
    @Basic
    @Column(name = "procitana", nullable = false)
    private Boolean procitana;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "korisnik_id", nullable = false)
    private Integer korisnikId;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "korisnik_id", nullable = false, insertable = false, updatable = false)
    private KorisnikEntity korisnik;
    @Basic
    @Column(name = "datum", nullable = false)
    private Date datum;
    @Basic
    @Column(name = "naslov", nullable = false, length = 100)
    private String naslov;
    @Basic
    @Column(name = "odgovorena", nullable = false)
    private Boolean odgovorena;

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Boolean getProcitana() {
        return procitana;
    }

    public void setProcitana(Boolean procitana) {
        this.procitana = procitana;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }

    public KorisnikEntity getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(KorisnikEntity korisnik) {
        this.korisnik = korisnik;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public Boolean getOdgovorena() {
        return odgovorena;
    }

    public void setOdgovorena(Boolean odgovorena) {
        this.odgovorena = odgovorena;
    }
}
