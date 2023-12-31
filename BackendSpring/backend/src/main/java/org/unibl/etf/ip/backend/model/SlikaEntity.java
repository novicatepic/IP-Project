package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
@Table(name = "slika", schema = "ip_project", catalog = "")
public class SlikaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "url is mandatory!")
    @Basic
    @Column(name = "url", nullable = false, length = 1000)
    private String url;

    @NotBlank(message = "programId is mandatory!")
    @Basic
    @Column(name = "program_id", nullable = false)
    private Integer programId;

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
