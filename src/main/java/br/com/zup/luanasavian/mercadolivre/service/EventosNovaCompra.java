package br.com.zup.luanasavian.mercadolivre.service;

import br.com.zup.luanasavian.mercadolivre.compartilhada.EventoCompraFalha;
import br.com.zup.luanasavian.mercadolivre.compartilhada.EventoCompraSucesso;
import br.com.zup.luanasavian.mercadolivre.model.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventosNovaCompra {

    @Autowired
    private Set<EventoCompraSucesso> eventosCompraSucesso;
    @Autowired
    private Set<EventoCompraFalha> eventosCompraFalha;

    public void processa(Compra compra) {
        if(compra.concluida()) {
            eventosCompraSucesso.forEach(evento -> evento.processa(compra));
        } else {
            eventosCompraFalha.forEach(evento -> evento.processa(compra));
        }
    }
}
