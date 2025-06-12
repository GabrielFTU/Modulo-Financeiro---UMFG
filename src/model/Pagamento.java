package model;

public class Pagamento {
    private int id;
    private double valorPago;
    private String formaPagamento;
    private StatusPagamento statusPagamento;
    private String comprovanteUrl;

    public enum StatusPagamento {
        PENDENTE, PAGO, CORTESIA, CANCELADO
    }

    public Pagamento(int id, double valorPago, String formaPagamento, StatusPagamento statusPagamento) {
        this.id = id;
        this.valorPago = valorPago;
        this.formaPagamento = formaPagamento;
        this.statusPagamento = statusPagamento;
    }

    public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public double getValorPago() { return valorPago; }
	public void setValorPago(double valorPago) { this.valorPago = valorPago;}
	public String getFormaPagamento() { return formaPagamento; }
	public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento;}
	public StatusPagamento getStatusPagamento() { return statusPagamento; }
	public void setStatusPagamento(StatusPagamento statusPagamento) { this.statusPagamento = statusPagamento; }
	public String getComprovanteUrl() { return comprovanteUrl; }
	public void setComprovanteUrl(String comprovanteUrl) { this.comprovanteUrl = comprovanteUrl; }
}