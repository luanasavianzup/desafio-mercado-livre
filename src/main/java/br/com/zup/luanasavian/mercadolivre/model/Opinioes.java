package br.com.zup.luanasavian.mercadolivre.model;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Opinioes {

    private Set<OpiniaoProduto> opinioes;

    public Opinioes(Set<OpiniaoProduto> opinioes) {
        this.opinioes = opinioes;
    }

    public <T> Set<T> mapOpinioes(Function<OpiniaoProduto, T> mapFunction) {
        return this.opinioes.stream().map(mapFunction).collect(Collectors.toSet());
    }

    public double media() {
        Set<Integer> notas = mapOpinioes(opiniao -> opiniao.getNota());
        OptionalDouble possivelMedia = notas.stream().mapToInt(nota -> nota).average();
        return possivelMedia.orElse(0.0);
    }

    public int totalOpinioes() {
        return this.opinioes.size();
    }
}
