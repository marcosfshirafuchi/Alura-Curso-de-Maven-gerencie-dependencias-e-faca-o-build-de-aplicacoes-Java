package br.com.alura;

import br.com.alura.model.Anuncio;
import br.com.alura.model.Produto;

import java.math.BigDecimal;

public class Main {
    /**
     * <h1> Alura - Curso de Maven: gerencie dependências e faça o build de aplicações Java </h1>
     * Programa que fornece um anúncio de um produto, sua descrição, preço e quantidade.<br><br>
     * <p>
     * <b>Note:</b> Desenvolvido na linguagem Java e seu framework: Spring Boot.
     *
     * @author  Marcos Ferreira Shirafuchi
     * @version 1.0
     * @since   19/11/2024
     */
    public static void main(String[] args) {
        var produto = new Produto(1, "TV", "4K", new BigDecimal(2000), "Eletrodoméstico");
        var anuncio = new Anuncio(produto, new BigDecimal ( 4000), 20);

        System.out.println(anuncio.toString());
    }
}