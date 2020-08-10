package model;

public class Sediste {
	
	private int redniBroj;
	private Sala sala;
	
	public Sediste(int redniBroj, Sala sala) {
		super();
		this.redniBroj = redniBroj;
		this.sala = sala;
	}
	
	public Sediste(int redniBroj) {
		super();
		this.redniBroj = redniBroj;
	}

	public int getRedniBroj() {
		return redniBroj;
	}

	public void setRedniBroj(int redniBroj) {
		this.redniBroj = redniBroj;
	}

	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}

	@Override
	public String toString() {
		return "Sediste [redniBroj=" + redniBroj + ", sala=" + sala + "]";
	}

}
