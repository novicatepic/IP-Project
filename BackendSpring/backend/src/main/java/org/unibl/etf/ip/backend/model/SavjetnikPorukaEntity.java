package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.sql.Date;

@Entity
@Table(name = "savjetnik_poruka", schema = "ip_project", catalog = "")
public class SavjetnikPorukaEntity {
    @NotBlank(message = "tekst is mandatory!")
    @Size(max = 1000, message = "Maximum character size for tekst is 1000!")
    @Basic
    @Column(name = "tekst", nullable = false, length = 1000)
    private String tekst;

    @NotBlank(message = "procitana is mandatory!")
    @Basic
    @Column(name = "procitana", nullable = false)
    private Boolean procitana;


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "korisnikId is mandatory!")
    @Max(value = 1000000, message = "korisnikId value must be less than or equal to 1000000")
    @Basic
    @Column(name = "korisnik_id", nullable = false)
    private Integer korisnikId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "korisnik_id", nullable = false, insertable = false, updatable = false)
    private KorisnikEntity korisnik;

    @NotBlank(message = "datum is mandatory!")
    @Basic
    @Column(name = "datum", nullable = false)
    private Date datum;

    @NotBlank(message = "naslov is mandatory!")
    @Size(max = 100, message = "Maximum character size for naslov is 100!")
    @Basic
    @Column(name = "naslov", nullable = false, length = 100)
    private String naslov;

    @NotBlank(message = "odgovorena is mandatory!")
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
