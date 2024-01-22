package org.unibl.etf.ip.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dnevnik", schema = "ip_project", catalog = "")
public class DnevnikEntity {

    @Id
    @Column(name = "korisnik_id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
