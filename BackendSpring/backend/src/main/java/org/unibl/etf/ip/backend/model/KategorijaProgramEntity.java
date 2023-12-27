package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

/*@Entity
@Table(name = "kategorija_program", schema = "ip_project", catalog = "")
@IdClass(KategorijaProgramEntityPK.class)*/
public class KategorijaProgramEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@Id
    @Column(name = "kategorija_id", nullable = false)
    private Integer kategorijaId;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "program_id", nullable = false)
    private Integer programId;

    @ManyToOne
    @JoinColumn(name = "kategorija_id", referencedColumnName = "id", insertable = false, updatable = false)
    private KategorijaEntity kategorija;

    public Integer getKategorijaId() {
        return kategorijaId;
    }

    public void setKategorijaId(Integer kategorijaId) {
        this.kategorijaId = kategorijaId;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public KategorijaEntity getKategorija() {
        return kategorija;
    }

    public void setKategorija(KategorijaEntity kategorija) {
        this.kategorija = kategorija;
    }*/
}
