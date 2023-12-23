package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "nalog_aktivacija", schema = "ip_project", catalog = "")
public class NalogAktivacijaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "kod", nullable = false, length = 5)
    private String kod;

    @OneToOne
    @JoinColumn(name = "korisnik_id", nullable = false)
    private KorisnikEntity fitnessUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public KorisnikEntity getFitnessUser() {
        return fitnessUser;
    }

    public void setFitnessUser(KorisnikEntity fitnessUser) {
        this.fitnessUser = fitnessUser;
    }
}
