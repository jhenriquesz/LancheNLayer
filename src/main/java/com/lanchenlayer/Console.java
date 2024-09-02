package com.lanchenlayer;

import com.lanchenlayer.applications.ProdutoApplication;
import com.lanchenlayer.applications.ProdutoApplicationInterface;
import com.lanchenlayer.entities.Produto;
import com.lanchenlayer.repositories.ProdutoRepository;
import com.lanchenlayer.repositories.ProdutoRepositoryInterface;
import com.lanchenlayer.services.ProdutoService;

import java.util.Scanner;

public class Console {

    public static void main(String[] args) {
        ProdutoRepositoryInterface produtoRepository = new ProdutoRepository();
        ProdutoService produtoService = new ProdutoService();
        ProdutoApplicationInterface produtoApplication = new ProdutoApplication(produtoRepository, produtoService);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            produtoApplication.exibirMenu();
            System.out.println("\n1 - Comprar produto");
            System.out.println("2 - Atualizar produto");
            System.out.println("3 - Remover produto");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            if (opcao == 4) {
                break;
            }

            switch (opcao) {
                case 1:
                    while (true) {
                        System.out.print("Digite o código do item: ");
                        int codigo = scanner.nextInt();
                        System.out.print("Digite a quantidade: ");
                        int quantidade = scanner.nextInt();
                        produtoApplication.vender(codigo, quantidade);
                        System.out.print("Deseja comprar outro produto? (s/n): ");
                        scanner.nextLine(); // Limpar buffer
                        String resposta = scanner.nextLine();
                        if (resposta.equalsIgnoreCase("n")) {
                            break;
                        }
                    }
                    break;
                case 2:
                    System.out.print("Digite o código do produto a atualizar: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Limpar buffer
                    System.out.print("Nova descrição: ");
                    String novaDescricao = scanner.nextLine();
                    System.out.print("Novo valor: ");
                    float novoValor = scanner.nextFloat();
                    scanner.nextLine(); // Limpar buffer
                    System.out.print("Caminho da nova imagem (ou deixe em branco para manter a atual): ");
                    String novaImagem = scanner.nextLine();
                    Produto novoProduto = new Produto(id, novaDescricao, novoValor, novaImagem.isEmpty() ? null : novaImagem);
                    produtoApplication.atualizarProduto(id, novoProduto);
                    break;
                case 3:
                    System.out.print("Digite o código do produto a remover: ");
                    int idRemover = scanner.nextInt();
                    produtoApplication.remover(idRemover);
                    System.out.println("Produto removido.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}
