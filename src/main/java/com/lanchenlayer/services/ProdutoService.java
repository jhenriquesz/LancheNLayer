package com.lanchenlayer.services;

import com.lanchenlayer.entities.Produto;
import com.lanchenlayer.repositories.ProdutoRepository;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class ProdutoService {
    private String caminhoDestino = "C:\\Users\\aluno\\LancheNLayer\\src\\main\\resources\\images\\";
    private ProdutoRepository produtoRepository;

    public ProdutoService() {
        this.produtoRepository = new ProdutoRepository();
        this.inicializarTabelaDePrecos();
    }

    public static String getFileExtension(String filePath) {
        String fileName = new File(filePath).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    public boolean salvarImagem(Produto produto) {
        Path pathOrigem = Paths.get(produto.getImagem());
        Path pathDestino = Paths.get(caminhoDestino + produto.getId() + "." + getFileExtension(produto.getImagem()));

        if (Files.exists(pathOrigem)) {
            try {
                Files.copy(pathOrigem, pathDestino, StandardCopyOption.REPLACE_EXISTING);
                produto.setImagem(pathDestino.toString());
                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        } else {
            System.out.println("Arquivo de imagem não encontrado: " + produto.getImagem());
            return false;
        }
    }

    private void inicializarTabelaDePrecos() {
        produtoRepository.adicionar(new Produto(1, "Cachorro quente", 4.00f, ""));
        produtoRepository.adicionar(new Produto(2, "X-Salada", 5.00f, ""));
        produtoRepository.adicionar(new Produto(3, "X-Bacon", 6.00f, ""));
        produtoRepository.adicionar(new Produto(4, "Torrada simples", 2.00f, ""));
        produtoRepository.adicionar(new Produto(5, "Refrigerante", 1.50f, ""));
    }

    public float calcularValorTotal(int codigo, int quantidade) {
        Produto produto = produtoRepository.buscarPorId(codigo);
        if (produto != null) {
            return produto.getValor() * quantidade;
        } else {
            System.out.println("Produto não encontrado para o código: " + codigo);
            return 0;
        }
    }

    public ArrayList<Produto> buscarTodos() {
        return produtoRepository.buscarTodos();
    }
}
