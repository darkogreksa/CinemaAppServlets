package model;

public class Karta {
	
	private int id;
	private Projekcija projekcija;
	private Sediste sediste;
	private String vremeProdaje;
	private Korisnik korisnik;
	
	public Karta(int id, Projekcija projekcija, Sediste sediste, String vremeProdaje, Korisnik korisnik) {
		super();
		this.id = id;
		this.projekcija = projekcija;
		this.sediste = sediste;
		this.vremeProdaje = vremeProdaje;
		this.korisnik = korisnik;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Projekcija getProjekcija() {
		return projekcija;
	}

	public void setProjekcija(Projekcija projekcija) {
		this.projekcija = projekcija;
	}

	public Sediste getSediste() {
		return sediste;
	}

	public void setSediste(Sediste sediste) {
		this.sediste = sediste;
	}

	public String getVremeProdaje() {
		return vremeProdaje;
	}

	public void setVremeProdaje(String vremeProdaje) {
		this.vremeProdaje = vremeProdaje;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	@Override
	public String toString() {
		return "Karta [id=" + id + ", projekcija=" + projekcija + ", sediste=" + sediste + ", vremeProdaje="
				+ vremeProdaje + ", korisnik=" + korisnik + "]";
	}
	
}
