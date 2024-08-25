package Interfaces;


import com.lanchenlayer.entities.Produto;
import java.util.List;

public interface IProdutoApplication {
    void adicionar(Produto produto);
    void adicionarSoImagem(Produto produto);
    void remover(int id);
    Produto buscarPorId(int id);
    List<Produto> buscarTodos();
}
