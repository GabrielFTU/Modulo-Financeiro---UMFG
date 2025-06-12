package model;

public class Lote {
    private int id;
    private String nome;
    private Evento evento; 
    private double valor;

    public Lote(int id, String nome, Evento evento, double valor) {
        this.id = id;
        this.nome = nome;
        this.evento = evento;
        this.valor = valor;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public Evento getEvento() { return evento; }
    public void setEvento(Evento evento) { this.evento = evento; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
}
