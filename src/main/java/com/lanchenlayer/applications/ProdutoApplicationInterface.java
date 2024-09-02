package com.lanchenlayer.applications;

import com.lanchenlayer.entities.Produto;

import java.util.ArrayList;

public interface ProdutoApplicationInterface {
    void adicionar(Produto produto);
    void remover(int id);
    Produto buscarPorId(int id);
    ArrayList<Produto> buscarTodos();
    void atualizarProduto(int id, Produto produto);
    void vender(int codigo, int quantidade);
    void exibirMenu();
}
