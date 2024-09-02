package com.lanchenlayer.repositories;

import com.lanchenlayer.entities.Produto;

import java.util.ArrayList;

public interface ProdutoRepositoryInterface {
    void adicionar(Produto produto);
    void remover(int id);
    Produto buscarPorId(int id);
    ArrayList<Produto> buscarTodos();
    void atualizar(Produto produto);
}
