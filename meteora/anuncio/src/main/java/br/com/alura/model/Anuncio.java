package br.com.alura.model;

import java.math.BigDecimal;

public class Anuncio {

    private Produto produto;
    private BigDecimal preco;
    private Integer quantidade;

    public Anuncio(Produto produto, BigDecimal preco, Integer quantidade){
        this.produto = produto;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return "Anuncio{" +
                "produto=" + produto +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                '}';
    }
}
