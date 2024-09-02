package com.lanchenlayer.applications;

import com.lanchenlayer.entities.Produto;
import com.lanchenlayer.repositories.ProdutoRepository;
import com.lanchenlayer.services.ProdutoService;

import java.util.ArrayList;
import java.util.List;

public class ProdutoApplication {
    private ProdutoRepository produtoRepository;
    private ProdutoService produtoService;

    public ProdutoApplication(ProdutoRepository produtoRepository, ProdutoService produtoService) {
        this.produtoRepository = produtoRepository;
        this.produtoService = produtoService;
    }

    public void exibirMenu() {
        List<Produto> produtos = produtoRepository.buscarTodos();
        System.out.println("\n---- Menu de Produtos ----");
        for (Produto produto : produtos) {
            System.out.printf("Código: %d | Descrição: %s | Preço: R$ %.2f\n",
                    produto.getId(), produto.getDescricao(), produto.getValor());
        }
    }

    public void vender(int codigo, int quantidade) {
        Produto produto = produtoRepository.buscarPorId(codigo);
        if (produto != null) {
            float total = produto.getValor() * quantidade;
            System.out.printf("Total a pagar: R$ %.2f\n", total);
        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    public void atualizarProduto(int id, Produto novoProduto) {
        produtoRepository.atualizar(id, novoProduto);
        if (novoProduto.getImagem() != null) {
            produtoService.atualizarImagem(id, novoProduto.getImagem());
        }
        System.out.println("Produto atualizado com sucesso!");
    }

    public void remover(int id) {
        produtoService.removerImagem(id);
        produtoRepository.remover(id);
        System.out.println("Produto removido com sucesso!");
    }

    public void adicionar(Produto produto) {
    }

    public Produto buscarPorId(int id) {
        return null;
    }

    public ArrayList<Produto> buscarTodos() {
        return null;
    }
}
