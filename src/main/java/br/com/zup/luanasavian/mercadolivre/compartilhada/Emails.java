package br.com.zup.luanasavian.mercadolivre.compartilhada;

import br.com.zup.luanasavian.mercadolivre.model.PerguntaProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
public class Emails {

    @Autowired
    private Mailer mailer;

    public void novaPergunta(@NotNull @Valid PerguntaProduto pergunta) {
        mailer.send("<html>...<html>", "Nova pergunta...",
                pergunta.getInteressado().getEmail(),
                "novapergunta@nossomercadolivre.com",
                pergunta.getDonoProduto().getEmail());
    }
}
