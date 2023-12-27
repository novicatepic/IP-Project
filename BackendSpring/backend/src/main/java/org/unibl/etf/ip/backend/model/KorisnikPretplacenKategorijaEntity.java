package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "korisnik_pretplacen_kategorija", schema = "ip_project", catalog = "")
@IdClass(KorisnikPretplacenKategorijaEntityPK.class)
public class KorisnikPretplacenKategorijaEntity {

    @Id
    @Column(name = "korisnik_id", nullable = false)
    private Integer korisnikId;
    @Id
    @Column(name = "kategorija_id", nullable = false)
    private Integer kategorijaId;


    @ManyToOne
    @JoinColumn(name = "korisnik_id", referencedColumnName = "id", insertable = false, updatable = false)
    private KorisnikEntity fitnessUser;

    @ManyToOne
    @JoinColumn(name = "kategorija_id", referencedColumnName = "id", insertable = false, updatable = false)
    private KategorijaEntity category;

    public Integer getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Integer getKategorijaId() {
        return kategorijaId;
    }

    public void setKategorijaId(Integer kategorijaId) {
        this.kategorijaId = kategorijaId;
    }

    public KorisnikEntity getFitnessUser() {
        return fitnessUser;
    }

    public void setFitnessUser(KorisnikEntity fitnessUser) {
        this.fitnessUser = fitnessUser;
    }

    public KategorijaEntity getCategory() {
        return category;
    }

    public void setCategory(KategorijaEntity category) {
        this.category = category;
    }
}
