package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "instruktor", schema = "ip_project", catalog = "")
public class InstruktorEntity {
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
    @Column(name = "godina_rodjenja", nullable = false)
    private Integer godinaRodjenja;
    @Basic
    @Column(name = "godine_iskustva", nullable = false)
    private Integer godineIskustva;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "program_id", nullable = false)
    private ProgramEntity program;

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

    public Integer getGodinaRodjenja() {
        return godinaRodjenja;
    }

    public void setGodinaRodjenja(Integer godinaRodjenja) {
        this.godinaRodjenja = godinaRodjenja;
    }

    public Integer getGodineIskustva() {
        return godineIskustva;
    }

    public void setGodineIskustva(Integer godineIskustva) {
        this.godineIskustva = godineIskustva;
    }

    public ProgramEntity getProgram() {
        return program;
    }

    public void setProgramId(ProgramEntity program) {
        this.program = program;
    }
}
