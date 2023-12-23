package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "pitanje", schema = "ip_project", catalog = "")
public class PitanjeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "tekst", nullable = false, length = 1000)
    private String tekst;
    @Basic
    @Column(name = "odgovor", nullable = true, length = 1000)
    private String odgovor;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "program_id", nullable = false)
    private ProgramEntity program;

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

    public ProgramEntity getProgram() {
        return program;
    }

    public void setProgramId(ProgramEntity program) {
        this.program = program;
    }

    public String getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(String odgovor) {
        this.odgovor = odgovor;
    }

    public void setProgram(ProgramEntity program) {
        this.program = program;
    }
}
