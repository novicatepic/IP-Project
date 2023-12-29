package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lokacija", schema = "ip_project", catalog = "")
public class LokacijaEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "naziv_lokacije", nullable = false, length = 200)
    private String nazivLokacije;
    @Basic
    @Column(name = "poruka", nullable = true, length = 2000)
    private String poruka;
    @Basic
    @Column(name = "program_id", nullable = false)
    private Integer programId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazivLokacije() {
        return nazivLokacije;
    }

    public void setNazivLokacije(String nazivLokacije) {
        this.nazivLokacije = nazivLokacije;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }
}
