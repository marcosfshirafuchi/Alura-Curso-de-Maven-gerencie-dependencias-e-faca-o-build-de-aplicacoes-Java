package br.com.alura.model;

import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AnuncioTest {

    @org.junit.jupiter.api.Test
    void getQuantidade() {
        var produto = new Produto(1, "TV", "4K", new BigDecimal(2000), "Eletrodoméstico");
        var anuncio = new Anuncio(produto, new BigDecimal ( 4000), 20);

        Assertions.assertEquals(20,anuncio.getQuantidade());
    }
}