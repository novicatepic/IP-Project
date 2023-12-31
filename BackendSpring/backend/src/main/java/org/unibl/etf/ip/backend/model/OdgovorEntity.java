package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Entity
@Table(name = "odgovor", schema = "ip_project", catalog = "")
public class OdgovorEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "odgovor is mandatory!")
    @Size(max = 1000, message = "Maximum character size for odgovor is 1000!")
    @Basic
    @Column(name = "odgovor", length = 1000)
    private String odgovor;

    @NotBlank(message = "pitanjeId is mandatory!")
    @Max(value = 10000000, message = "pitanjeId value must be less than or equal to 10000000")
    @Basic
    @Column(name = "pitanje_id")
    private Integer pitanjeId;

    @NotBlank(message = "korisnikId is mandatory!")
    @Max(value = 1000000, message = "korisnikId value must be less than or equal to 1000000")
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
