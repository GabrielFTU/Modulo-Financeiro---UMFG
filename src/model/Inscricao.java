package model;

import java.time.LocalDate;

public class Inscricao {
    private int id;
    private String nomeInscrito;
    private Lote lote;
    private LocalDate dataInscricao;
    private String tipoIngresso;
    private String motivoCortesia;
    private boolean cancelado;
    private Pagamento pagamento; 

    public Inscricao(int id, String nomeInscrito, Lote lote, LocalDate dataInscricao) {
        this.id = id;
        this.nomeInscrito = nomeInscrito;
        this.lote = lote;
        this.dataInscricao = dataInscricao;
        this.tipoIngresso = "NORMAL"; 
        this.cancelado = false;      
    }

    // Getters e Setters
    public int getId() { return id; } 
    public void setId(int id) { this.id = id; }

    public String getNomeInscrito() { return nomeInscrito; }
    public void setNomeInscrito(String nomeInscrito) { this.nomeInscrito = nomeInscrito; }

    public Lote getLote() { return lote; }
    public void setLote(Lote lote) { this.lote = lote; }

    public LocalDate getDataInscricao() { return dataInscricao; }
    public void setDataInscricao(LocalDate dataInscricao) { this.dataInscricao = dataInscricao; }

    public String getTipoIngresso() { return tipoIngresso; }
    public void setTipoIngresso(String tipoIngresso) { this.tipoIngresso = tipoIngresso; }

    public String getMotivoCortesia() { return motivoCortesia; }
    public void setMotivoCortesia(String motivoCortesia) { this.motivoCortesia = motivoCortesia; }

    public boolean isCancelado() { return cancelado; }
    public void setCancelado(boolean cancelado) { this.cancelado = cancelado; }

    public Pagamento getPagamento() { return pagamento; }
    public void setPagamento(Pagamento pagamento) { this.pagamento = pagamento; }
}