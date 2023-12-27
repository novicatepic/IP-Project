package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "odgovor", schema = "ip_project", catalog = "")
public class OdgovorEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "odgovor", length = 1000)
    private String odgovor;
    @Basic
    @Column(name = "pitanje_id")
    private Integer pitanjeId;
    @Basic
    @Column(name = "korisnik_id")
    private Integer korisnikId;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "korisnik_id", insertable = false, updatable = false)
    private KorisnikEntity korisnik;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(String odgovor) {
        this.odgovor = odgovor;
    }

    public Integer getPitanjeId() {
        return pitanjeId;
    }

    public void setPitanjeId(Integer pitanjeId) {
        this.pitanjeId = pitanjeId;
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
}
