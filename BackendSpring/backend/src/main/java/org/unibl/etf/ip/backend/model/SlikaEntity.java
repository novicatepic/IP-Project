package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "slika", schema = "ip_project", catalog = "")
public class SlikaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "url", nullable = false, length = 1000)
    private String url;
    @Basic
    @Column(name = "program_id", nullable = false)
    private Integer programId;
    /*@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "program_id", nullable = false)
    private ProgramEntity program;*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }
}
