package br.com.zup.luanasavian.mercadolivre.service;

import br.com.zup.luanasavian.mercadolivre.compartilhada.EventoCompraSucesso;
import br.com.zup.luanasavian.mercadolivre.model.Compra;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Ranking implements EventoCompraSucesso {
    @Override
    public void processa(Compra compra) {
        Assert.isTrue(compra.concluida(), "Compra não processada com sucesso"+compra);

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getComprador().getId(), "idComprador", compra.getComprador().getId());

        restTemplate.postForEntity("http://localhost:8080//notas-fiscais", request, String.class);
    }
}
