package br.com.zup.luanasavian.mercadolivre.compartilhada;

import br.com.zup.luanasavian.mercadolivre.model.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnviaEmailSucesso implements EventoCompraSucesso{

    @Autowired
    private Mailer mailer;

    @Override
    public void processa(Compra compra) {
        mailer.send("", "", "", "", "");
    }
}
