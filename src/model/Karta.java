package model;

import java.sql.Timestamp;

public class Karta {
	
	private int id;
	private Projekcija projekcija;
	private Sediste sediste;
	private Timestamp vremeProdaje;
	private Korisnik korisnik;
	
	public Karta(int id, Projekcija projekcija, Sediste sediste, Timestamp vremeProdaje, Korisnik korisnik) {
		super();
		this.id = id;
		this.projekcija = projekcija;
		this.sediste = sediste;
		this.vremeProdaje = vremeProdaje;
		this.korisnik = korisnik;
	}

	public Karta(int id, Projekcija projekcija, Sediste sediste, Korisnik korisnik) {
		super();
		this.id = id;
		this.projekcija = projekcija;
		this.sediste = sediste;
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

	public Timestamp getVremeProdaje() {
		return vremeProdaje;
	}

	public void setVremeProdaje(Timestamp vremeProdaje) {
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
