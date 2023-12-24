package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "savjetnik", schema = "ip_project", catalog = "")
public class SavjetnikEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "korisnicko_ime", nullable = false, length = 45)
    private String korisnickoIme;
    @Basic
    @Column(name = "lozinka", nullable = false, length = 45)
    private String lozinka;
    @Basic
    @Column(name = "ime", nullable = false, length = 45)
    private String ime;
    @Basic
    @Column(name = "prezime", nullable = false, length = 45)
    private String prezime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}
