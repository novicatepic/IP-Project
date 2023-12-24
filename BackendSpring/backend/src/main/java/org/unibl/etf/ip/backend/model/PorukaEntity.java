package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "poruka", schema = "ip_project", catalog = "")
public class PorukaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "tekst", nullable = false, length = 1000)
    private String tekst;

    @Basic
    @Column(name = "posiljalac_id", nullable = false)
    private Integer posiljalacId;
    @Basic
    @Column(name = "primalac_id", nullable = false)
    private Integer primalacId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "posiljalac_id", nullable = false, updatable = false, insertable = false)
    private KorisnikEntity posiljalac;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "primalac_id", nullable = false, updatable = false, insertable = false)
    private KorisnikEntity primalac;
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
