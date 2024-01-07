package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.util.Date;

@Entity
@Table(name = "dnevnik_unos", schema = "ip_project", catalog = "")
public class DnevnikUnosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "vjezba is mandatory!")
    @Size(max = 45, message = "Maximum character size for vjezba is 45!")
    @Basic
    @Column(name = "vjezba", nullable = false, length = 45)
    private String vjezba;

    @NotBlank(message = "trajanje is mandatory!")
    @Size(max = 45, message = "Maximum character size for trajanje is 45!")
    @Basic
    @Column(name = "trajanje", nullable = false, length = 45)
    private String trajanje;

    @NotBlank(message = "intenzitet is mandatory!")
    @Size(max = 45, message = "Maximum character size for intenzitet is 45!")
    @Basic
    @Column(name = "intenzitet", nullable = false, length = 45)
    private String intenzitet;

    @NotNull(message = "kilaza is mandatory!")
    @Max(value = 200, message = "kilaza value must be less than or equal to 200!")
    @Min(value = 10, message = "kilaza value must be greater than or equal to 10!")
    @Basic
    @Column(name = "kilaza", nullable = false)
    private Integer kilaza;

    @NotNull(message = "dnevnikKorisnikId is mandatory!")
    @Max(value = 1000000, message = "dnevnikKorisnikId value must be less than or equal to 1000000!")
    @Min(value = 1, message = "dnevnikKorisnikId value must be greater than or equal to 1!")
    @Basic
    @Column(name = "dnevnik_korisnik_id", nullable = false)
    private Integer dnevnikKorisnikId;

    @NotNull(message = "datum is mandatory!")
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "datum", nullable = false)
    private Date datum;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDnevnikKorisnikId() {
        return dnevnikKorisnikId;
    }

    public void setDnevnikKorisnikId(Integer dnevnikKorisnikId) {
        this.dnevnikKorisnikId = dnevnikKorisnikId;
    }

    public String getVjezba() {
        return vjezba;
    }

    public void setVjezba(String vjezba) {
        this.vjezba = vjezba;
    }

    public String getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(String trajanje) {
        this.trajanje = trajanje;
    }

    public String getIntenzitet() {
        return intenzitet;
    }

    public void setIntenzitet(String intenzitet) {
        this.intenzitet = intenzitet;
    }

    public Integer getKilaza() {
        return kilaza;
    }

    public void setKilaza(Integer kilaza) {
        this.kilaza = kilaza;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
}
