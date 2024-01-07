package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Entity
@Table(name = "korisnik_pretplacen_kategorija", schema = "ip_project", catalog = "")
@IdClass(KorisnikPretplacenKategorijaEntityPK.class)
public class KorisnikPretplacenKategorijaEntity {
    @NotNull(message = "korisnikId is mandatory!")
    @Max(value = 1000000, message = "korisnikId value must be less than or equal to 1000000")
    @Min(value = 1, message = "korisnikId value must be greater than or equal to 1!")
    @Id
    @Column(name = "korisnik_id", nullable = false)
    private Integer korisnikId;


    @NotNull(message = "kategorijaId is mandatory!")
    @Max(value = 1000000, message = "kategorijaId value must be less than or equal to 1000000")
    @Min(value = 1, message = "kategorijaId value must be greater than or equal to 1!")
    @Id
    @Column(name = "kategorija_id", nullable = false)
    private Integer kategorijaId;


    @ManyToOne
    @JoinColumn(name = "korisnik_id", referencedColumnName = "id", insertable = false, updatable = false)
    private KorisnikEntity fitnessUser;

    @ManyToOne
    @JoinColumn(name = "kategorija_id", referencedColumnName = "id", insertable = false, updatable = false)
    private KategorijaEntity category;

    public Integer getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Integer getKategorijaId() {
        return kategorijaId;
    }

    public void setKategorijaId(Integer kategorijaId) {
        this.kategorijaId = kategorijaId;
    }

    public KorisnikEntity getFitnessUser() {
        return fitnessUser;
    }

    public void setFitnessUser(KorisnikEntity fitnessUser) {
        this.fitnessUser = fitnessUser;
    }

    public KategorijaEntity getCategory() {
        return category;
    }

    public void setCategory(KategorijaEntity category) {
        this.category = category;
    }
}
