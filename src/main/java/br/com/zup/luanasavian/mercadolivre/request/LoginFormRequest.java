package br.com.zup.luanasavian.mercadolivre.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginFormRequest {

    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
