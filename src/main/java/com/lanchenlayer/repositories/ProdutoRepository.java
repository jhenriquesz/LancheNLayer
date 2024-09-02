package com.lanchenlayer.repositories;

import com.lanchenlayer.entities.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    private List<Produto> produtos = new ArrayList<>();

    public void adicionar(Produto produto) {
        produtos.add(produto);
    }

    public void remover(int id) {
        produtos.removeIf(produto -> produto.getId() == id);
    }

    public Produto buscarPorId(int id) {
        return produtos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public List<Produto> buscarTodos() {
        return produtos;
    }

    public void atualizar(int id, Produto novoProduto) {
        Produto produtoExistente = buscarPorId(id);
        if (produtoExistente != null) {
            produtoExistente.setDescricao(novoProduto.getDescricao());
            produtoExistente.setValor(novoProduto.getValor());
            if (novoProduto.getImagem() != null) {
                produtoExistente.setImagem(novoProduto.getImagem());
            }
        }
    }
}
