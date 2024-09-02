package com.lanchenlayer.services;

import com.lanchenlayer.entities.Produto;
import com.lanchenlayer.repositories.ProdutoRepository;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProdutoService {
    private String caminhoDestino = "C:\\Users\\aluno\\LancheNLayer\\src\\main\\resources\\images\\";
    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public boolean salvarImagem(Produto produto) {
        // Código de salvar imagem como estava antes
        return false;
    }

    public void removerImagem(int id) {
        Produto produto = produtoRepository.buscarPorId(id);
        if (produto != null && produto.getImagem() != null) {
            try {
                Files.deleteIfExists(Paths.get(caminhoDestino + produto.getImagem()));
            } catch (Exception e) {
                System.out.println("Erro ao remover a imagem: " + e.getMessage());
            }
        }
    }

    public void atualizarImagem(int id, String novaImagem) {
        removerImagem(id);
        Produto produto = produtoRepository.buscarPorId(id);
        if (produto != null) {
            produto.setImagem(novaImagem);
            salvarImagem(produto);
        }
    }
}
