package br.com.zup.luanasavian.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long idCompra;
    @NotNull
    private  Long idDonoProduto;

    public Ranking(@NotNull Long idCompra, @NotNull Long idDonoProduto) {
        this.idCompra = idCompra;
        this.idDonoProduto = idDonoProduto;
    }
}
