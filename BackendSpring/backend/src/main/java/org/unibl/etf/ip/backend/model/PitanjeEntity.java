package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pitanje", schema = "ip_project", catalog = "")
public class PitanjeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "tekst is mandatory!")
    @Size(max = 1000, message = "Maximum character size for tekst is 1000!")
    @Basic
    @Column(name = "tekst", nullable = false, length = 1000)
    private String tekst;

    @NotBlank(message = "programId is mandatory!")
    @Max(value = 1000000, message = "programId value must be less than or equal to 1000000")
    @Min(value = 1, message = "programId value must be greater than or equal to 1!")
    @Basic
    @Column(name = "program_id", nullable = false)
    private Integer programId;

    @NotBlank(message = "korisnikId is mandatory!")
    @Max(value = 1000000, message = "korisnikId value must be less than or equal to 1000000")
    @Min(value = 1, message = "korisnikId value must be greater than or equal to 1!")
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
