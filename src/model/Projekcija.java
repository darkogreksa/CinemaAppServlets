package model;


public class Projekcija {
	
	private int id;
	private Film film;
	private TipProjekcije tipProjekcije;
	private Sala sala;
	private String vremePrikazivanja;
	private double cenaKarte;
	private Korisnik administrator;
	private boolean obrisan;
	
	public Projekcija(int id, Film film, TipProjekcije tipProjekcije, Sala sala, String vremePrikazivanja,
			double cenaKarte, Korisnik administrator, boolean obrisan) {
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
	
	public Projekcija(Film film, TipProjekcije tipProjekcije, Sala sala, String vremePrikazivanja, double cenaKarte,
			Korisnik administrator) {
		super();
		this.film = film;
		this.tipProjekcije = tipProjekcije;
		this.sala = sala;
		this.vremePrikazivanja = vremePrikazivanja;
		this.cenaKarte = cenaKarte;
		this.administrator = administrator;
	}

	public Projekcija(int id, Film film, TipProjekcije tipProjekcije, Sala sala, String vremePrikazivanja) {
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

	public String getVremePrikazivanja() {
		return vremePrikazivanja;
	}

	public void setVremePrikazivanja(String vremePrikazivanja) {
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

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	@Override
	public String toString() {
		return "Projekcija [id=" + id + ", film=" + film + ", tipProjekcije=" + tipProjekcije + ", sala=" + sala
				+ ", vremePrikazivanja=" + vremePrikazivanja + ", cenaKarte=" + cenaKarte + ", administrator="
				+ administrator + ", obrisan=" + obrisan + "]";
	}
}
