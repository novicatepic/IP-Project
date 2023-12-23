package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "korisnik", schema = "ip_project", catalog = "")
public class KorisnikEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "ime", nullable = false, length = 45)
    private String ime;
    @Basic
    @Column(name = "prezime", nullable = false, length = 45)
    private String prezime;
    @Basic
    @Column(name = "grad", nullable = false, length = 100)
    private String grad;
    @Basic
    @Column(name = "korisnicko_ime", nullable = false, length = 45)
    private String korisnickoIme;
    @Basic
    @Column(name = "lozinka", nullable = false, length = 45)
    private String lozinka;
    @Basic
    @Column(name = "avatar", nullable = true, length = 200)
    private String avatar;
    @Basic
    @Column(name = "mail", nullable = false, length = 200)
    private String mail;
    @Basic
    @Column(name = "aktivan", nullable = false)
    private Boolean aktivan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(Boolean aktivan) {
        this.aktivan = aktivan;
    }

    @Override
    public String toString() {
        return "KorisnikEntity{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", grad='" + grad + '\'' +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", avatar='" + avatar + '\'' +
                ", mail='" + mail + '\'' +
                ", aktivan=" + aktivan +
                '}';
    }
}
