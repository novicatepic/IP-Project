package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dnevnik_unos", schema = "ip_project", catalog = "")
public class DnevnikUnosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "vjezba", nullable = false, length = 45)
    private String vjezba;
    @Basic
    @Column(name = "trajanje", nullable = false, length = 45)
    private String trajanje;
    @Basic
    @Column(name = "intenzitet", nullable = false, length = 45)
    private String intenzitet;
    @Basic
    @Column(name = "kilaza", nullable = false)
    private Integer kilaza;
    @Basic
    @Column(name = "dnevnik_korisnik_id", nullable = false)
    private Integer dnevnikKorisnikId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "dnevnik_korisnik_id", referencedColumnName = "korisnik_id", insertable = false, updatable = false)
    private DnevnikEntity dnevnik;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDnevnikKorisnikId() {
        return dnevnikKorisnikId;
    }

    public void setDnevnikKorisnikId(Integer dnevnikKorisnikId) {
        this.dnevnikKorisnikId = dnevnikKorisnikId;
    }

    public String getVjezba() {
        return vjezba;
    }

    public void setVjezba(String vjezba) {
        this.vjezba = vjezba;
    }

    public String getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(String trajanje) {
        this.trajanje = trajanje;
    }

    public String getIntenzitet() {
        return intenzitet;
    }

    public void setIntenzitet(String intenzitet) {
        this.intenzitet = intenzitet;
    }

    public Integer getKilaza() {
        return kilaza;
    }

    public void setKilaza(Integer kilaza) {
        this.kilaza = kilaza;
    }

    public DnevnikEntity getDnevnik() {
        return dnevnik;
    }

    public void setDnevnik(DnevnikEntity dnevnik) {
        this.dnevnik = dnevnik;
    }
}
