package br.com.zup.luanasavian.mercadolivre.compartilhada;

import br.com.zup.luanasavian.mercadolivre.model.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class EnviaEmailFalha implements EventoCompraFalha {

    @Autowired
    private Mailer mailer;


    @Override
    public void processa(Compra compra) {
        URI uri = compra.link();
        mailer.send("localhost:8080" + uri, "", "", "", "");
    }
}
