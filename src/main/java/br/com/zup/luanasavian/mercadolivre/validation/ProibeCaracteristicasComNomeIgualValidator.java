package br.com.zup.luanasavian.mercadolivre.validation;

import br.com.zup.luanasavian.mercadolivre.request.ProdutoFormRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class ProibeCaracteristicasComNomeIgualValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ProdutoFormRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        ProdutoFormRequest request = (ProdutoFormRequest) target;
        Set<String> nomesIguais = request.buscaCaracteristicasIguais();
        if(!nomesIguais.isEmpty()) {
            errors.reject("caracteristicas", null, "Características iguais." +  nomesIguais);
        }
    }
}
