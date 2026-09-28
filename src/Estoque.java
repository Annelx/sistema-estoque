
import java.util.ArrayList;
import java.util.List;

public class Estoque {

    // Composição: o Estoque TEM UMA lista de produtos
    private List<Product> produtos = new ArrayList<>();

    public void adicionarProduto(Product p) {
        produtos.add(p);
    }

    public void venderProduto(int indice, int quantidade) throws ProdutoIndisponivelException {
        Product produto = produtos.get(indice);
        produto.vender(quantidade);
    }

    public double calcularValorTotalEstoque() {
        double total = 0;
        for (Product p : produtos) {
            total += p.calcularValorTotal(); // polimorfismo dinâmico
        }
        return total;
    }

    public void listarProdutos() {
        for (int i = 0; i < produtos.size(); i++) {
            Product p = produtos.get(i);
            System.out.printf("[%d] %s -> Valor total: R$ %.2f%n",
                    i, p.getDescricao(), p.calcularValorTotal());
        }
    }
}
