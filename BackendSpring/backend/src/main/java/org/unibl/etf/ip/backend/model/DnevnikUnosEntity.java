package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "dnevnik_unos", schema = "ip_project", catalog = "")
public class DnevnikUnosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "dnevnik_korisnik_id", nullable = false)
    private Integer dnevnikKorisnikId;
    @Basic
    @Column(name = "vjezba", nullable = false, length = 45)
    private String vjezba;
    @Basic
    @Column(name = "trajanje", nullable = false)
    private Integer trajanje;
    @Basic
    @Column(name = "intenzitet", nullable = false, length = 45)
    private String intenzitet;
    @Basic
    @Column(name = "potroseno_kalorija", nullable = false)
    private Integer potrosenoKalorija;

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

    public Integer getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(Integer trajanje) {
        this.trajanje = trajanje;
    }

    public String getIntenzitet() {
        return intenzitet;
    }

    public void setIntenzitet(String intenzitet) {
        this.intenzitet = intenzitet;
    }

    public Integer getPotrosenoKalorija() {
        return potrosenoKalorija;
    }

    public void setPotrosenoKalorija(Integer potrosenoKalorija) {
        this.potrosenoKalorija = potrosenoKalorija;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DnevnikUnosEntity that = (DnevnikUnosEntity) o;
        return Objects.equals(dnevnikKorisnikId, that.dnevnikKorisnikId) && Objects.equals(vjezba, that.vjezba) && Objects.equals(trajanje, that.trajanje) && Objects.equals(intenzitet, that.intenzitet) && Objects.equals(potrosenoKalorija, that.potrosenoKalorija);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dnevnikKorisnikId, vjezba, trajanje, intenzitet, potrosenoKalorija);
    }
}
