package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kategorija", schema = "ip_project", catalog = "")
public class KategorijaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "naziv", nullable = false, length = 200)
    private String naziv;
    @Basic
    @Column(name = "program_id", nullable = false, length = 200)
    private Integer programId;

    /*@OneToMany(mappedBy = "kategorijaId", cascade = CascadeType.ALL)
    private List<AtributEntity> attributes = new ArrayList<>();*/

    public Integer getId() {
        return id;
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

    /*public List<AtributEntity> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AtributEntity> attributes) {
        this.attributes = attributes;
    }*/

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }
}
