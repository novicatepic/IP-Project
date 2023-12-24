package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "dnevnik", schema = "ip_project", catalog = "")
public class DnevnikEntity implements Serializable {

    @Id
    @Column(name = "korisnik_id", nullable = false)
    private Integer korisnikId;

    @OneToOne
    @JoinColumn(name = "korisnik_id", referencedColumnName = "id")
    private KorisnikEntity korisnikEntity;


    public KorisnikEntity getKorisnikEntity() {
        return korisnikEntity;
    }

    public void setKorisnikEntity(KorisnikEntity korisnikEntity) {
        this.korisnikEntity = korisnikEntity;
    }

    public Integer getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }

}
