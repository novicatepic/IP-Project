package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Entity
@Table(name = "korisnik_pretplacen_program", schema = "ip_project", catalog = "")
@IdClass(KorisnikPretplacenProgramEntityPK.class)
public class KorisnikPretplacenProgramEntity {
    @NotBlank(message = "korisnikId is mandatory!")
    @Max(value = 1000000, message = "korisnikId value must be less than or equal to 1000000")
    @Min(value = 1, message = "korisnikId value must be greater than or equal to 1!")
    @Id
    @Column(name = "korisnik_id", nullable = false)
    private Integer korisnikId;


    @NotBlank(message = "programId is mandatory!")
    @Max(value = 1000000, message = "programId value must be less than or equal to 1000000")
    @Min(value = 1, message = "programId value must be greater than or equal to 1!")
    @Id
    @Column(name = "program_id", nullable = false)
    private Integer programId;

    @NotBlank(message = "nacinPlacanja is mandatory!")
    @Size(max = 100, message = "Maximum character size for nacinPlacanja is 100!")
    @Basic
    @Column(name = "nacin_placanja", nullable = false, length = 100)
    private String nacinPlacanja;

    @NotBlank(message = "vrijednost is mandatory!")
    @Max(value = 1000000, message = "vrijednost value must be less than or equal to 1000000")
    @Min(value = 1, message = "vrijednost value must be greater than or equal to 1!")
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
