package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

import java.util.Objects;

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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "posiljalac_id", nullable = false)
    private KorisnikEntity posiljalac;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "primalac_id", nullable = false)
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
