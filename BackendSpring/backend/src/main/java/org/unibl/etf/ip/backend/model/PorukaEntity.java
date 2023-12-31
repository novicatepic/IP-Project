package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Entity
@Table(name = "poruka", schema = "ip_project", catalog = "")
public class PorukaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "tekst is mandatory!")
    @Size(max = 1000, message = "Maximum character size for tekst is 1000!")
    @Basic
    @Column(name = "tekst", nullable = false, length = 1000)
    private String tekst;

    @NotBlank(message = "posiljalacId is mandatory!")
    @Max(value = 1000000, message = "posiljalacId value must be less than or equal to 1000000")
    @Min(value = 1, message = "posiljalacId value must be greater than or equal to 1!")
    @Basic
    @Column(name = "posiljalac_id", nullable = false)
    private Integer posiljalacId;

    @NotBlank(message = "primalacId is mandatory!")
    @Max(value = 1000000, message = "primalacId value must be less than or equal to 1000000")
    @Min(value = 1, message = "primalacId value must be greater than or equal to 1!")
    @Basic
    @Column(name = "primalac_id", nullable = false)
    private Integer primalacId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "posiljalac_id", nullable = false, updatable = false, insertable = false)
    private KorisnikEntity posiljalac;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "primalac_id", nullable = false, updatable = false, insertable = false)
    private KorisnikEntity primalac;

    @NotBlank(message = "procitana is mandatory!")
    @Basic
    @Column(name = "procitana", nullable = false)
    private Boolean procitana;

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

    public Integer getPosiljalacId() {
        return posiljalacId;
    }

    public void setPosiljalacId(Integer posiljalacId) {
        this.posiljalacId = posiljalacId;
    }

    public Integer getPrimalacId() {
        return primalacId;
    }

    public void setPrimalacId(Integer primalacId) {
        this.primalacId = primalacId;
    }

    public KorisnikEntity getPosiljalac() {
        return posiljalac;
    }

    public void setPosiljalac(KorisnikEntity posiljalac) {
        this.posiljalac = posiljalac;
    }

    public KorisnikEntity getPrimalac() {
        return primalac;
    }

    public void setPrimalac(KorisnikEntity primalac) {
        this.primalac = primalac;
    }

    public Boolean getProcitana() {
        return procitana;
    }

    public void setProcitana(Boolean procitana) {
        this.procitana = procitana;
    }
}
