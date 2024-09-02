package com.lanchenlayer.applications;

import com.lanchenlayer.entities.Produto;
import com.lanchenlayer.repositories.ProdutoRepositoryInterface;
import com.lanchenlayer.services.ProdutoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProdutoApplication implements ProdutoApplicationInterface {
    private ProdutoRepositoryInterface produtoRepository;
    private ProdutoService produtoService;

    public ProdutoApplication(ProdutoRepositoryInterface produtoRepository, ProdutoService produtoService) {
        this.produtoRepository = produtoRepository;
        this.produtoService = produtoService;
        inicializarProdutos(); // Inicializar com alguns produtos
    }

    private void inicializarProdutos() {
        Map<Integer, Produto> tabelaDePrecos = new HashMap<>();
        tabelaDePrecos.put(1, new Produto(1, "Cachorro quente", 4.00f, null));
        tabelaDePrecos.put(2, new Produto(2, "X-Salada", 5.00f, null));
        tabelaDePrecos.put(3, new Produto(3, "X-Bacon", 6.00f, null));
        tabelaDePrecos.put(4, new Produto(4, "Torrada simples", 2.00f, null));
        tabelaDePrecos.put(5, new Produto(5, "Refrigerante", 1.50f, null));

        for (Produto produto : tabelaDePrecos.values()) {
            produtoRepository.adicionar(produto);
        }
    }

    @Override
    public void adicionar(Produto produto) {
        this.produtoRepository.adicionar(produto);
        this.produtoService.salvarImagem(produto);
    }

    @Override
    public void remover(int id) {
        this.produtoRepository.remover(id);
    }

    @Override
    public Produto buscarPorId(int id) {
        return this.produtoRepository.buscarPorId(id);
    }

    @Override
    public ArrayList<Produto> buscarTodos() {
        return this.produtoRepository.buscarTodos();
    }

    @Override
    public void atualizarProduto(int id, Produto produto) {
        Produto produtoExistente = this.produtoRepository.buscarPorId(id);
        if (produtoExistente != null) {
            produto.setId(id); // Certificar-se de que o ID seja o mesmo
            this.produtoRepository.atualizar(produto);
            this.produtoService.removerImagem(produtoExistente);
            this.produtoService.salvarImagem(produto);
        }
    }

    @Override
    public void vender(int codigo, int quantidade) {
        Produto produto = produtoRepository.buscarPorId(codigo);
        if (produto != null) {
            double valorTotal = produto.getValor() * quantidade;
            System.out.printf("Total: R$ %.2f%n", valorTotal);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    @Override
    public void exibirMenu() {
        System.out.println("Menu:");
        for (Produto produto : produtoRepository.buscarTodos()) {
            System.out.printf("Código: %d, Descrição: %s, Valor: %.2f%n",
                    produto.getId(), produto.getDescricao(), produto.getValor());
        }
    }
}
