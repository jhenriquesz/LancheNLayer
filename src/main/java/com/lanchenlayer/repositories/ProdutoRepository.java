package com.lanchenlayer.repositories;

import com.lanchenlayer.entities.Produto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProdutoRepository implements ProdutoRepositoryInterface {
    private Map<Integer, Produto> produtos = new HashMap<>();

    @Override
    public void adicionar(Produto produto) {
        produtos.put(produto.getId(), produto);
    }

    @Override
    public void remover(int id) {
        produtos.remove(id);
    }

    @Override
    public Produto buscarPorId(int id) {
        return produtos.get(id);
    }

    @Override
    public ArrayList<Produto> buscarTodos() {
        return new ArrayList<>(produtos.values());
    }

    @Override
    public void atualizar(Produto produto) {
        produtos.put(produto.getId(), produto);
    }
}
