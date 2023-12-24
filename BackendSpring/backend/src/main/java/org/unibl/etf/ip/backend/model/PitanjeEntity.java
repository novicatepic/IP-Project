package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

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
    @Basic
    @Column(name = "program_id", nullable = false)
    private Integer programId;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "program_id", insertable = false, updatable = false)
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


    public String getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(String odgovor) {
        this.odgovor = odgovor;
    }


    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public ProgramEntity getProgram() {
        return program;
    }

    public void setProgram(ProgramEntity program) {
        this.program = program;
    }
}
