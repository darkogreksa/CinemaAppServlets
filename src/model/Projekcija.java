package model;

public class Projekcija {
	
	private int id;
	private Film film;
	private TipProjekcije tipProjekcije;
	private Sala sala;
	private String vremePrikazivanja;
	private double cena;
	private String administrator;
	
	
	public Projekcija(int id, Film film, TipProjekcije tipProjekcije, Sala sala, String vremePrikazivanja, double cena,
			String administrator) {
		super();
		this.id = id;
		this.film = film;
		this.tipProjekcije = tipProjekcije;
		this.sala = sala;
		this.vremePrikazivanja = vremePrikazivanja;
		this.cena = cena;
		this.administrator = administrator;
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


	public String getVremePrikazivanja() {
		return vremePrikazivanja;
	}


	public void setVremePrikazivanja(String vremePrikazivanja) {
		this.vremePrikazivanja = vremePrikazivanja;
	}


	public double getCena() {
		return cena;
	}


	public void setCena(double cena) {
		this.cena = cena;
	}


	public String getAdministrator() {
		return administrator;
	}


	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}


	@Override
	public String toString() {
		return "Projekcija [id=" + id + ", film=" + film + ", tipProjekcije=" + tipProjekcije + ", sala=" + sala
				+ ", vremePrikazivanja=" + vremePrikazivanja + ", cena=" + cena + ", administrator=" + administrator
				+ "]";
	}
	
	

}
