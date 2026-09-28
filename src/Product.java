public abstract class Product implements Vendavel {

    private String nome;
    private double preco;
    private int quantidade;

    public Product(String nome, double preco, int quantidade) throws QuantidadeInvalidaException {
        if (preco < 0) {
            throw new QuantidadeInvalidaException(
                    "Preço inválido para o produto '" + nome + "': " + preco);
        }
        if (quantidade < 0) {
            throw new QuantidadeInvalidaException(
                    "Quantidade inválida para o produto '" + nome + "': " + quantidade);
        }
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Método abstrato: cada subclasse decide como calcular
    public abstract double calcularValorTotal();

    // Método concreto
    public String getDescricao() {
        return String.format("%s | Preço: R$ %.2f | Quantidade: %d", nome, preco, quantidade);
    }

    // Implementação da interface Vendavel
    @Override
    public void vender(int quantidadeDesejada) throws ProdutoIndisponivelException {
        if (quantidadeDesejada <= 0) {
            throw new IllegalArgumentException("A quantidade a vender deve ser maior que zero.");
        }
        if (quantidadeDesejada > quantidade) {
            throw new ProdutoIndisponivelException(
                    "Estoque insuficiente de '" + nome + "': solicitado " + quantidadeDesejada
                            + ", disponível " + quantidade);
        }
        quantidade -= quantidadeDesejada;
    }

    // Sobrecarga (polimorfismo estático) - versão 1
    public void aplicarDesconto(double percentual) {
        validarPercentual(percentual);
        preco -= preco * (percentual / 100);
    }

    // Sobrecarga (polimorfismo estático) - versão 2: desconto limitado a um valor máximo
    public void aplicarDesconto(double percentual, double descontoMaximo) {
        validarPercentual(percentual);
        double desconto = preco * (percentual / 100);
        if (desconto > descontoMaximo) {
            desconto = descontoMaximo;
        }
        preco -= desconto;
    }

    private void validarPercentual(double percentual) {
        if (percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException("Percentual de desconto deve estar entre 0 e 100.");
        }
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
