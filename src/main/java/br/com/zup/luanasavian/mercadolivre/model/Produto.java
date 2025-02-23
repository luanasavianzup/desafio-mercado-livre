package br.com.zup.luanasavian.mercadolivre.model;

import br.com.zup.luanasavian.mercadolivre.request.CaracteristicaFormRequest;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotBlank
    private String nome;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private int quantidade;
    @NotBlank
    private String descricao;
    private LocalDateTime instante = LocalDateTime.now();
    @NotNull
    @Valid
    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private Usuario dono;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<Caracteristica> caracteristicas = new HashSet<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<OpiniaoProduto> opinioes = new HashSet<>();
    @OneToMany(mappedBy = "produto")
    @OrderBy("titulo asc")
    private SortedSet<PerguntaProduto> perguntas = new TreeSet<>();

    @Deprecated
    private Produto() {

    }

    public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull @PositiveOrZero int quantidade, @NotBlank String descricao, @NotNull @Valid Categoria categoria, Usuario dono, List<CaracteristicaFormRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.dono = dono;
        Set<Caracteristica> novasCaracteristicas = caracteristicas
                .stream().map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toSet());
        this.caracteristicas.addAll(novasCaracteristicas);

        Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa ter no mínimo 3 características");
    }

    public Long getId() {
        return Id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getDono() {
        return dono;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }

    public void associaImagens(Set<String> links) {
        Set<ImagemProduto> imagens =  links.stream().map(link -> new ImagemProduto(this, link)).collect(Collectors.toSet());
        this.imagens.addAll(imagens);
    }

    public boolean pertenceAoUsuario(Usuario possivelDono) {
        return this.dono.equals(possivelDono);
    }

    public <T> Set<T> mapCaracteristicas(Function<Caracteristica, T> mapFunction) {
        return this.caracteristicas.stream().map(mapFunction).collect(Collectors.toSet());
    }

    public <T> Set<T> mapImagens(Function<ImagemProduto, T> mapFunction) {
        return this.imagens.stream().map(mapFunction).collect(Collectors.toSet());
    }

    public <T extends Comparable<T>> SortedSet<T> mapPerguntas(Function<PerguntaProduto, T> mapFunction) {
        return this.perguntas.stream().map(mapFunction).collect(Collectors.toCollection(TreeSet :: new));
    }

    public Opinioes getOpinioes() {
        return new Opinioes(this.opinioes);
    }

    public boolean abateEstoque(@Positive int quantidade) {
        Assert.isTrue(quantidade > 0, "Quantidade deve ser maior que 0 para abater do estoque" + quantidade);
        if(quantidade <= this.quantidade) {
            this.quantidade -= quantidade;
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(nome, produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
