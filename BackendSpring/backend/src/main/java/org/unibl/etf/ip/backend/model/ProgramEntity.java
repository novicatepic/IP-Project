package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "program", schema = "ip_project", catalog = "")
public class ProgramEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "naziv", nullable = false, length = 200)
    private String naziv;
    @Basic
    @Column(name = "opis", nullable = false, length = 2000)
    private String opis;
    @Basic
    @Column(name = "cijena", nullable = false, precision = 0)
    private Integer cijena;
    @Basic
    @Column(name = "tezina", nullable = false)
    private Integer tezina;
    @Basic
    @Column(name = "trajanje", nullable = false)
    private Integer trajanje;
    @Basic
    @Column(name = "naziv_lokacije", nullable = false, length = 200)
    private String nazivLokacije;
    @Basic
    @Column(name = "poruka_lokacije", nullable = false, length = 200)
    private String porukaLokacije;
    @Basic
    @Column(name = "kontakt", nullable = false, length = 45)
    private String kontakt;
    @Basic
    @Column(name = "datum", nullable = false)
    private Date datum;
    @Basic
    @Column(name = "ucestvovan", nullable = false)
    private Boolean ucestvovan;

    @Basic
    @Column(name = "kreator_id", nullable = false)
    private Integer kreatorId;

    @Basic
    @Column(name = "kategorija_id", nullable = false)
    private Integer kategorijaId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "kreator_id", nullable = false, insertable = false, updatable = false)
    private KorisnikEntity kreator;

    @OneToMany(mappedBy = "programId", cascade = CascadeType.ALL)
    private List<SlikaEntity> pictures = new ArrayList<>();

    /*@OneToMany(mappedBy = "programId", cascade = CascadeType.ALL)
    private List<LokacijaEntity> lokacije = new ArrayList<>();*/

    /*@OneToOne(mappedBy = "programId", cascade = CascadeType.ALL)
    private LokacijaEntity lokacija;*/



    @ManyToOne
    @JoinColumn(name = "kategorija_id", referencedColumnName = "id", insertable = false, updatable = false)
    private KategorijaEntity kategorija;

    @OneToMany(mappedBy = "programId", cascade = CascadeType.ALL)
    private List<PitanjeEntity> programQuestions = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public String getNazivLokacije() {
        return nazivLokacije;
    }

    public void setNazivLokacije(String nazivLokacije) {
        this.nazivLokacije = nazivLokacije;
    }

    public String getPorukaLokacije() {
        return porukaLokacije;
    }

    public void setPorukaLokacije(String porukaLokacije) {
        this.porukaLokacije = porukaLokacije;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Integer getCijena() {
        return cijena;
    }

    public void setCijena(Integer cijena) {
        this.cijena = cijena;
    }

    public Integer getTezina() {
        return tezina;
    }

    public void setTezina(Integer tezina) {
        this.tezina = tezina;
    }

    public Integer getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(Integer trajanje) {
        this.trajanje = trajanje;
    }

    /*public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }*/

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Boolean getUcestvovan() {
        return ucestvovan;
    }


    public void setUcestvovan(Boolean ucestvovan) {
        this.ucestvovan = ucestvovan;
    }

    public Boolean getAktivan() {
        return this.datum.getTime() > new java.util.Date().getTime();
    }

    public Integer getKreatorId() {
        return kreatorId;
    }

    public void setKreatorId(Integer kreatorId) {
        this.kreatorId = kreatorId;
    }

    public KorisnikEntity getKreator() {
        return kreator;
    }

    public void setKreator(KorisnikEntity kreator) {
        this.kreator = kreator;
    }

    public List<SlikaEntity> getPictures() {
        return pictures;
    }

    public void setPictures(List<SlikaEntity> pictures) {
        this.pictures = pictures;
    }

    /*public List<KategorijaProgramEntity> getKategorijaProgramEntities() {
        return kategorijaProgramEntities;
    }

    public void setKategorijaProgramEntities(List<KategorijaProgramEntity> kategorijaProgramEntities) {
        this.kategorijaProgramEntities = kategorijaProgramEntities;
    }*/

    public KategorijaEntity getKategorija() {
        return kategorija;
    }

    public void setKategorija(KategorijaEntity kategorije) {
        this.kategorija = kategorije;
    }

    public Integer getKategorijaId() {
        return kategorijaId;
    }

    public void setKategorijaId(Integer kategorijaId) {
        this.kategorijaId = kategorijaId;
    }

    public List<PitanjeEntity> getProgramQuestions() {
        return programQuestions;
    }

    public void setProgramQuestions(List<PitanjeEntity> programQuestions) {
        this.programQuestions = programQuestions;
    }

    /*public List<LokacijaEntity> getLokacije() {
        return lokacije;
    }

    public void setLokacije(List<LokacijaEntity> lokacije) {
        this.lokacije = lokacije;
    }*/

    /*public LokacijaEntity getLokacija() {
        return lokacija;
    }

    public void setLokacija(LokacijaEntity lokacija) {
        this.lokacija = lokacija;
    }*/
}
