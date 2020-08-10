package model;

import java.sql.Timestamp;

public class Projekcija {
	
	private int id;
	private Film film;
	private TipProjekcije tipProjekcije;
	private Sala sala;
	private Timestamp vremePrikazivanja;
	private double cenaKarte;
	private Korisnik administrator;
	private int obrisan;
	
	public Projekcija(int id, Film film, TipProjekcije tipProjekcije, Sala sala, Timestamp vremePrikazivanja,
			double cenaKarte, Korisnik administrator, int obrisan) {
		super();
		this.id = id;
		this.film = film;
		this.tipProjekcije = tipProjekcije;
		this.sala = sala;
		this.vremePrikazivanja = vremePrikazivanja;
		this.cenaKarte = cenaKarte;
		this.administrator = administrator;
		this.obrisan = obrisan;
	}
	
	public Projekcija(int id, Film film, TipProjekcije tipProjekcije, Sala sala, Timestamp vremePrikazivanja) {
		super();
		this.id = id;
		this.film = film;
		this.tipProjekcije = tipProjekcije;
		this.sala = sala;
		this.vremePrikazivanja = vremePrikazivanja;
	}





	public Projekcija(int id) {
		super();
		this.id = id;
	}



	public Projekcija() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public TipProjekcije getTipProjekcije() {
		return tipProjekcije;
	}

	public void setTipProjekcije(TipProjekcije tipProjekcije) {
		this.tipProjekcije = tipProjekcije;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Timestamp getVremePrikazivanja() {
		return vremePrikazivanja;
	}

	public void setVremePrikazivanja(Timestamp vremePrikazivanja) {
		this.vremePrikazivanja = vremePrikazivanja;
	}

	public double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	public Korisnik getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Korisnik administrator) {
		this.administrator = administrator;
	}

	public int getObrisan() {
		return obrisan;
	}

	public void setObrisan(int obrisan) {
		this.obrisan = obrisan;
	}

	@Override
	public String toString() {
		return "Projekcija [id=" + id + ", film=" + film + ", tipProjekcije=" + tipProjekcije + ", sala=" + sala
				+ ", vremePrikazivanja=" + vremePrikazivanja + ", cenaKarte=" + cenaKarte + ", administrator="
				+ administrator + ", obrisan=" + obrisan + "]";
	}

	

}
