public class EstoqueApp {

    public static void main(String[] args) {
        Estoque estoque = new Estoque();

        // 1. Cadastro de produtos
        System.out.println("=== CADASTRO DE PRODUTOS ===");
        try {
            estoque.adicionarProduto(new ProdutoComum("Arroz 5kg", 25.90, 10));
            estoque.adicionarProduto(new ProdutoComum("Feijão 1kg", 8.50, 20));
            estoque.adicionarProduto(new ProdutoPerecivel("Leite 1L", 4.99, 30, 10));
            estoque.adicionarProduto(new ProdutoPerecivel("Iogurte", 3.50, 12, 2)); // vence em <= 3 dias
            System.out.println("Produtos cadastrados com sucesso!");
        } catch (QuantidadeInvalidaException e) {
            System.out.println("Erro no cadastro: " + e.getMessage());
        }
        estoque.listarProdutos();

        // 2. Tentativa de cadastro com quantidade negativa
        System.out.println("\n=== CADASTRO INVÁLIDO ===");
        try {
            estoque.adicionarProduto(new ProdutoComum("Açúcar 1kg", 4.79, -5));
        } catch (QuantidadeInvalidaException e) {
            System.out.println("QuantidadeInvalidaException capturada: " + e.getMessage());
        }

        // 3. Vendas: uma válida e outra acima do estoque
        System.out.println("\n=== VENDAS ===");
        try {
            estoque.venderProduto(0, 3);
            System.out.println("Venda realizada: 3 unidades de Arroz 5kg.");

            estoque.venderProduto(3, 50); // mais do que o disponível
            System.out.println("Esta linha não deve ser impressa.");
        } catch (ProdutoIndisponivelException e) {
            System.out.println("ProdutoIndisponivelException capturada: " + e.getMessage());
        }

        // 4. Sobrecarga de aplicarDesconto()
        System.out.println("\n=== DESCONTOS (SOBRECARGA) ===");
        try {
            Product sabao = new ProdutoComum("Sabão em pó", 20.00, 5);
            sabao.aplicarDesconto(10);          // 10% -> R$ 18,00
            System.out.println("Após aplicarDesconto(10): " + sabao.getDescricao());
            sabao.aplicarDesconto(50, 2.00);    // 50% limitado a R$ 2,00 -> R$ 16,00
            System.out.println("Após aplicarDesconto(50, 2.00): " + sabao.getDescricao());
            estoque.adicionarProduto(sabao);
        } catch (QuantidadeInvalidaException e) {
            System.out.println("Erro no cadastro: " + e.getMessage());
        }

        // 5. Estoque final e valor total
        System.out.println("\n=== ESTOQUE FINAL ===");
        estoque.listarProdutos();
        System.out.printf("%nValor total do estoque: R$ %.2f%n", estoque.calcularValorTotalEstoque());
    }
}
