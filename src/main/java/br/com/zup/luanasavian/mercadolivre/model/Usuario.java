package br.com.zup.luanasavian.mercadolivre.model;

import br.com.zup.luanasavian.mercadolivre.extra.SenhaLimpa;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Length(min = 6)
    private String senha;
    @NotNull
    private LocalDateTime instante = LocalDateTime.now();

    @Deprecated
    private Usuario() {
    }

    public Usuario(@NotBlank @Email String email, @NotNull @Valid SenhaLimpa senhaLimpa) {
        Assert.isTrue(StringUtils.hasLength(email), "email não pode ficar em branco");
        Assert.notNull(senhaLimpa, "a senha não pode ser nula");

        this.email = email;
        this.senha = senhaLimpa.hash();
    }

    public Long getId() {
        return Id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
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
