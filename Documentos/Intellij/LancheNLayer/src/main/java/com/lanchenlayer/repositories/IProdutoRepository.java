package com.lanchenlayer.repositories;

import com.lanchenlayer.entities.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IProdutoRepository extends ProdutoRepository {
    private List<Produto> produtos = new ArrayList<>();

    @Override
    public void adicionar(Produto produto) {
        produtos.add(produto);
    }

    @Override
    public void remover(int id) {
        produtos.removeIf(produto -> produto.getId() == id);
    }

    @Override
    public Produto buscarPorId(int id) {
        Optional<Produto> produtoOptional = produtos.stream()
                .filter(p -> p.getId() == id)
                .findFirst();

        if (produtoOptional.isPresent()) {
            return produtoOptional.get();
        } else {
            // Lançar exceção ou retornar um valor nulo, dependendo da necessidade
            throw new RuntimeException("Produto não encontrado com o ID: " + id);
        }
    }

    @Override
    public ArrayList<Produto> buscarTodos() {
        return new ArrayList<>(produtos);
    }
}

