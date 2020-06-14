package model;

public class Sediste {
	
	private int id;
	private Sala sala;
	
	public Sediste(int id, Sala sala) {
		super();
		this.id = id;
		this.sala = sala;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	@Override
	public String toString() {
		return "Sediste [id=" + id + ", sala=" + sala + "]";
	}

}
