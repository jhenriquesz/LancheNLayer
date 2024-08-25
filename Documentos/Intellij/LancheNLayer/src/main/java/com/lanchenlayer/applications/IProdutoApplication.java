package com.lanchenlayer.applications;

import Interfaces.IProdutoService;
import com.lanchenlayer.entities.Produto;
import com.lanchenlayer.repositories.IProdutoRepository;
import com.lanchenlayer.repositories.ProdutoRepository;
import com.lanchenlayer.services.ProdutoService;

import java.util.ArrayList;

public class IProdutoApplication extends ProdutoApplication {
        private IProdutoRepository produtoRepository;
        private IProdutoService produtoService;

    public IProdutoApplication(ProdutoRepository produtoRepository, ProdutoService produtoService) {
        super(produtoRepository, produtoService);
    }

    // Construtor que recebe as interfaces
        public void ProdutoApplication(IProdutoRepository produtoRepository, IProdutoService produtoService) {
            this.produtoRepository = produtoRepository;
            this.produtoService = produtoService;
        }

        @Override
        public void adicionar(Produto produto) {
            this.produtoRepository.adicionar(produto);
            this.produtoService.salvarImagem(produto);
        }

        @Override
        public void adicionarSoImagem(Produto produto) {
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
    }

