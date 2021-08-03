package br.com.zup.luanasavian.mercadolivre.request;

import br.com.zup.luanasavian.mercadolivre.model.SenhaLimpa;
import br.com.zup.luanasavian.mercadolivre.model.Usuario;
import br.com.zup.luanasavian.mercadolivre.validation.UniqueValue;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UsuarioFormRequest {
    @NotBlank
    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "email")
    private String email;
    @NotBlank
    @Length(min = 6)
    private String senha;

    public UsuarioFormRequest(@NotBlank @Email String email, @NotBlank @Length(min = 6) String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario toModel() {

        return new Usuario(this.email, new SenhaLimpa(senha));
    }
}
