package br.com.zup.luanasavian.mercadolivre.compartilhada;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;

//Representa uma senha limpa no sistema
public class SenhaLimpa {

    private String senha;

    public SenhaLimpa(@NotBlank @Length(min = 6) String senha) {
        Assert.hasLength(senha, "senha não pode ficar em branco");
        Assert.isTrue(senha.length() >= 6, "senha precisa de no mínimo 6 caracteres");
        this.senha = senha;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(senha);
    }
}
