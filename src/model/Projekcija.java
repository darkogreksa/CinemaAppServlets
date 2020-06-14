package model;

public class Projekcija {
	
	private int id;
	private Film film;
	private TipProjekcije tipProjekcije;
	private Sala sala;
	private String vremePrikazivanja;
	private double cena;
	private Korisnik administrator;
	
	public Projekcija(int id, Film film, TipProjekcije tipProjekcije, Sala sala, String vremePrikazivanja, double cena,
			Korisnik administrator) {
		super();
		this.id = id;
		this.film = film;
		this.tipProjekcije = tipProjekcije;
		this.sala = sala;
		this.vremePrikazivanja = vremePrikazivanja;
		this.cena = cena;
		this.administrator = administrator;
	}


	public Projekcija(int id2, int film2, int tipProjekcije2, int sala2, String vrPrikazivanja, double cena2,
			String administrator2) {
		// TODO Auto-generated constructor stub
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


	public Korisnik getAdministrator() {
		return administrator;
	}


	public void setAdministrator(Korisnik administrator) {
		this.administrator = administrator;
	}


	@Override
	public String toString() {
		return "Projekcija [id=" + id + ", film=" + film + ", tipProjekcije=" + tipProjekcije + ", sala=" + sala
				+ ", vremePrikazivanja=" + vremePrikazivanja + ", cena=" + cena + ", administrator=" + administrator
				+ "]";
	}
	
	

}
