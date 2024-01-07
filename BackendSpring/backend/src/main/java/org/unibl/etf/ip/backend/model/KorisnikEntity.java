package org.unibl.etf.ip.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.unibl.etf.ip.backend.roles.ROLE;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "korisnik", schema = "ip_project", catalog = "")
public class KorisnikEntity implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "ime is mandatory!")
    @Size(max = 45, message = "Maximum character size for ime is 45!")
    @Basic
    @Column(name = "ime", nullable = false, length = 45)
    private String ime;

    @NotBlank(message = "prezime is mandatory!")
    @Size(max = 45, message = "Maximum character size for prezime is 45!")
    @Basic
    @Column(name = "prezime", nullable = false, length = 45)
    private String prezime;

    @NotBlank(message = "grad is mandatory!")
    @Size(max = 100, message = "Maximum character size for grad is 100!")
    @Basic
    @Column(name = "grad", nullable = false, length = 100)
    private String grad;

    @NotBlank(message = "korisnickoIme is mandatory!")
    @Size(max = 45, message = "Maximum character size for korisnickoIme is 45!")
    @Basic
    @Column(name = "korisnicko_ime", nullable = false, length = 45, unique = true)
    private String korisnickoIme;

    @NotBlank(message = "lozinka is mandatory!")
    @Size(max = 500, min = 8, message = "Maximum character size for lozinka is 500, and minimum is 8!")
    @Basic
    @Column(name = "lozinka", nullable = false, length = 500)
    private String lozinka;

    @Size(max = 21845, message = "Maximum character size for avatar is 21845!")
    @Basic
    @Column(name = "avatar", nullable = true, length = 21845)
    private String avatar;

    @NotBlank(message = "mail is mandatory!")
    @Email
    @Size(max = 200, message = "Maximum character size for mail is 200!")
    @Basic
    @Column(name = "mail", nullable = false, length = 200, unique = true)
    private String mail;

    @NotNull(message = "aktivan is mandatory!")
    @Basic
    @Column(name = "aktivan", nullable = false)
    private Boolean aktivan;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "korisnik_pretplacen_kategorija",
            joinColumns = @JoinColumn(name = "korisnik_id"),
            inverseJoinColumns = @JoinColumn(name = "kategorija_id")
    )
    private List<KategorijaEntity> subscribedCategories;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(Boolean aktivan) {
        this.aktivan = aktivan;
    }

    @Override
    public String toString() {
        return "KorisnikEntity{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", grad='" + grad + '\'' +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", avatar='" + avatar + '\'' +
                ", mail='" + mail + '\'' +
                ", aktivan=" + aktivan +
                '}';
    }

    public Boolean getAktivan() {
        return aktivan;
    }

    public List<KategorijaEntity> getSubscribedCategories() {
        return subscribedCategories;
    }

    public void setSubscribedCategories(List<KategorijaEntity> subscribedCategories) {
        this.subscribedCategories = subscribedCategories;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(ROLE.ROLE_USER.toString()));
    }

    @Override
    public String getPassword() {
        return lozinka;
    }

    @Override
    public String getUsername() {
        return korisnickoIme;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
