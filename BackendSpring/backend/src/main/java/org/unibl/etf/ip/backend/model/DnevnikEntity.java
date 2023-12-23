package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "dnevnik", schema = "ip_project", catalog = "")
public class DnevnikEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "korisnik_id", nullable = false)
    private Integer korisnikId;

    public KorisnikEntity getKorisnikEntity() {
        return korisnikEntity;
    }

    public void setKorisnikEntity(KorisnikEntity korisnikEntity) {
        this.korisnikEntity = korisnikEntity;
    }

    //added
    @OneToOne
    @JoinColumn(name = "korisnik_id", referencedColumnName = "id")
    private KorisnikEntity korisnikEntity;

    @Basic
    @Column(name = "dnevnikcol", nullable = true, length = 45)
    private String dnevnikcol;

    public Integer getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }

    public String getDnevnikcol() {
        return dnevnikcol;
    }

    public void setDnevnikcol(String dnevnikcol) {
        this.dnevnikcol = dnevnikcol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DnevnikEntity that = (DnevnikEntity) o;
        return Objects.equals(korisnikId, that.korisnikId) && Objects.equals(dnevnikcol, that.dnevnikcol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(korisnikId, dnevnikcol);
    }
}
