package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "atribut", schema = "ip_project", catalog = "")
public class AtributEntity {
    @Basic
    @Column(name = "naziv", nullable = false, length = 100)
    private String naziv;
    @Basic
    @Column(name = "vrijednost", nullable = false, length = 300)
    private String vrijednost;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "kategorija_id", nullable = false)
    private KategorijaEntity kategorija;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(String vrijednost) {
        this.vrijednost = vrijednost;
    }

    public KategorijaEntity getKategorijaId() {
        return kategorija;
    }

    public void setKategorijaId(KategorijaEntity kategorija) {
        this.kategorija = kategorija;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
