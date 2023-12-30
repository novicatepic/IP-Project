package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "korisnik_pretplacen_program", schema = "ip_project", catalog = "")
@IdClass(KorisnikPretplacenProgramEntityPK.class)
public class KorisnikPretplacenProgramEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "korisnik_id", nullable = false)
    private Integer korisnikId;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "program_id", nullable = false)
    private Integer programId;
    @Basic
    @Column(name = "nacin_placanja", nullable = false, length = 100)
    private String nacinPlacanja;
    @Basic
    @Column(name = "vrijednost", nullable = false)
    private Integer vrijednost;

    @ManyToOne
    @JoinColumn(name = "korisnik_id", referencedColumnName = "id", insertable = false, updatable = false)
    private KorisnikEntity fitnessUser;

    @ManyToOne
    @JoinColumn(name = "program_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ProgramEntity fitnessProgram;

    public Integer getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public String getNacinPlacanja() {
        return nacinPlacanja;
    }

    public void setNacinPlacanja(String nacinPlacanja) {
        this.nacinPlacanja = nacinPlacanja;
    }

    public Integer getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(Integer vrijednost) {
        this.vrijednost = vrijednost;
    }

    public KorisnikEntity getFitnessUser() {
        return fitnessUser;
    }

    public void setFitnessUser(KorisnikEntity fitnessUser) {
        this.fitnessUser = fitnessUser;
    }

    public ProgramEntity getFitnessProgram() {
        return fitnessProgram;
    }

    public void setFitnessProgram(ProgramEntity fitnessProgram) {
        this.fitnessProgram = fitnessProgram;
    }
}
