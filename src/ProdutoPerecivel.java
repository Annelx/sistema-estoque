public class ProdutoPerecivel extends Product {

    private int diasParaVencer;

    public ProdutoPerecivel(String nome, double preco, int quantidade, int diasParaVencer)
            throws QuantidadeInvalidaException {
        super(nome, preco, quantidade);
        this.diasParaVencer = diasParaVencer;
    }

    @Override
    public double calcularValorTotal() {
        double total = getPreco() * getQuantidade();
        if (diasParaVencer <= 3) {
            total = total * 0.80; // 20% de desconto automático
        }
        return total;
    }

    @Override
    public String getDescricao() {
        String descricao = super.getDescricao() + " | Vence em: " + diasParaVencer + " dia(s)";
        if (diasParaVencer <= 3) {
            descricao += " (20% de desconto no valor total)";
        }
        return descricao;
    }

    public int getDiasParaVencer() {
        return diasParaVencer;
    }
}
