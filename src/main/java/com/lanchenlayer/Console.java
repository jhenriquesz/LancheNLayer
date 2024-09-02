package com.lanchenlayer;

import com.lanchenlayer.applications.ProdutoApplication;
import com.lanchenlayer.entities.Produto;
import com.lanchenlayer.facade.ProdutoFacade;
import com.lanchenlayer.repositories.ProdutoRepository;
import com.lanchenlayer.services.ProdutoService;

import java.util.ArrayList;
import java.util.Scanner;

public class Console {

    public static void main(String[] args) {
        ProdutoService produtoService = new ProdutoService();
        ProdutoRepository produtoRepository = new ProdutoRepository();
        ProdutoApplication produtoApplication = new ProdutoApplication(produtoRepository, produtoService);
        ProdutoFacade produtoFacade = new ProdutoFacade(produtoApplication);
        Scanner scanner = new Scanner(System.in);

        boolean outroItem = true;
        float totalGeral = 0;

        while (outroItem) {
            exibirMenu(produtoService);

            // Lê a entrada do usuário
            System.out.print("Digite o código do item: ");
            int codigo = scanner.nextInt();
            System.out.print("Digite a quantidade: ");
            int quantidade = scanner.nextInt();

            // Calcula o valor total para o item atual
            float total = produtoService.calcularValorTotal(codigo, quantidade);
            totalGeral += total;

            System.out.printf("Subtotal: R$ %.2f%n", total);
            outroItem = perguntarOutroItem(scanner);
        }

        // Exibe o valor total final
        System.out.printf("Total Geral: R$ %.2f%n", totalGeral);
    }

    // Método para exibir o menu de lanches disponíveis
    public static void exibirMenu(ProdutoService produtoService) {
        ArrayList<Produto> produtos = produtoService.buscarTodos();
        System.out.println("Menu de Lanches:");
        produtos.forEach(produto ->
                System.out.printf("%d - %s: R$ %.2f%n", produto.getId(), produto.getDescricao(), produto.getValor())
        );
    }

    // Método para perguntar se o cliente deseja escolher outro item
    public static boolean perguntarOutroItem(Scanner scanner) {
        System.out.print("Gostaria de escolher outro item? (S/N): ");
        String resposta = scanner.next();
        return resposta.equalsIgnoreCase("S");
    }
}
