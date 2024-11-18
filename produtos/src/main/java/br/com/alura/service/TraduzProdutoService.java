package br.com.alura.service;

import br.com.alura.model.Produto;
import space.dynomake.libretranslate.Language;
import space.dynomake.libretranslate.Translator;

public class TraduzProdutoService {
    public void traduzir(Produto produto){
        String novoNome = Translator.translate(Language.ENGLISH, Language.PORTUGUESE, produto.getName());
        String novaCategoria = Translator.translate(Language.ENGLISH, Language.PORTUGUESE, produto.getCategory());
        String novaDescricao = Translator.translate(Language.ENGLISH, Language.PORTUGUESE, produto.getDescription());
        produto.setName(novoNome);
        produto.setCategory(novaCategoria);
        produto.setDescription(novaDescricao);
    }
}
