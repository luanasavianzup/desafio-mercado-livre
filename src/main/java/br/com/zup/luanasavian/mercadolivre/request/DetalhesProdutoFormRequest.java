package br.com.zup.luanasavian.mercadolivre.request;

import br.com.zup.luanasavian.mercadolivre.model.OpiniaoProduto;
import br.com.zup.luanasavian.mercadolivre.model.Opinioes;
import br.com.zup.luanasavian.mercadolivre.model.Produto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;


public class DetalhesProdutoFormRequest {

    private String descricao;
    private String nome;
    private BigDecimal valor;
    private Set<DetalheProdutoCaracteristica> caracteristicas;
    private Set<String> linksImagens;
    private SortedSet<String> perguntas;
    private Set<Map<String, String>> opinioes;
    private double mediaNotas;
    private int totalOpinioes;

    public DetalhesProdutoFormRequest(Produto produto) {
        this.descricao = produto.getDescricao();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.caracteristicas = produto.mapCaracteristicas(DetalheProdutoCaracteristica::new);
        this.linksImagens = produto.mapImagens(imagem -> imagem.getLink());
        this.perguntas = produto.mapPerguntas(pergunta -> pergunta.getTitulo());
        Opinioes opinioes = produto.getOpinioes();
        this.opinioes = opinioes.mapOpinioes(opiniao -> {
            return Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
        });
        this.mediaNotas = opinioes.media();
        this.totalOpinioes = opinioes.totalOpinioes();
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }

    public SortedSet<String> getPerguntas() {
        return perguntas;
    }

    public Set<Map<String, String>> getOpinioes() {
        return opinioes;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }

    public int getTotalOpinioes() {
        return totalOpinioes;
    }
}
