package br.com.alura;

import br.com.alura.model.Produto;
import br.com.alura.service.TraduzProdutoService;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Main {
    /**
     * <h1> Alura - Curso de Maven: gerencie dependências e faça o build de aplicações Java </h1>
     * Programa que traduz o arquivo csv de outro idioma para o português<br><br>
     * <p>
     * <b>Note:</b> Desenvolvido na linguagem Java e seu framework: Spring Boot.
     *
     * @author  Marcos Ferreira Shirafuchi
     * @version 1.0
     * @since   10/11/2024
     */
    public static void main(String[] args) throws FileNotFoundException {
        List<Produto> produtos = new CsvToBeanBuilder(new FileReader("src/main/resources/products.csv"))
                .withType(Produto.class).build().parse();

        for (Produto produto: produtos){
            System.out.println(produto);
        }
        TraduzProdutoService traducaoService = new TraduzProdutoService();
        for (Produto produto: produtos){
            traducaoService.traduzir(produto);
            System.out.println(produto);
        }
    }
}
