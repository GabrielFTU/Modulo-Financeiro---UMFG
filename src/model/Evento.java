package model;

public class Evento {
	private int id;
	private String nome;

	public Evento(int id, String nome) {
		this.setId(id);
		this.setNome(nome);
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
}
