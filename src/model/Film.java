package model;

public class Film {
	
	private int id;
	private String naziv;
	private String reziser;
	private String glumci;
	private String zanrovi;
	private int trajanje;
	private String distributer;
	private String zemljaPorekla;
	private int godinaProizvodnje;
	private String opis;
	private boolean obrisan;
	
	public Film(int id, String naziv, String reziser, String glumci, String zanrovi, int trajanje, String distributer,
			String zemljaPorekla, int godinaProizvodnje, String opis, boolean obrisan) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.reziser = reziser;
		this.glumci = glumci;
		this.zanrovi = zanrovi;
		this.trajanje = trajanje;
		this.distributer = distributer;
		this.zemljaPorekla = zemljaPorekla;
		this.godinaProizvodnje = godinaProizvodnje;
		this.opis = opis;
		this.obrisan = obrisan;
	}

	public Film(int id, String naziv, String reziser, String glumci, String zanrovi, int trajanje, String distributer,
			String zemljaPorekla, int godinaProizvodnje, String opis) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.reziser = reziser;
		this.glumci = glumci;
		this.zanrovi = zanrovi;
		this.trajanje = trajanje;
		this.distributer = distributer;
		this.zemljaPorekla = zemljaPorekla;
		this.godinaProizvodnje = godinaProizvodnje;
		this.opis = opis;
	}

	public Film(int id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public String getReziser() {
		return reziser;
	}


	public void setReziser(String reziser) {
		this.reziser = reziser;
	}


	public String getGlumci() {
		return glumci;
	}


	public void setGlumci(String glumci) {
		this.glumci = glumci;
	}


	public String getZanrovi() {
		return zanrovi;
	}


	public void setZanrovi(String zanrovi) {
		this.zanrovi = zanrovi;
	}


	public int getTrajanje() {
		return trajanje;
	}


	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}


	public String getDistributer() {
		return distributer;
	}


	public void setDistributer(String distributer) {
		this.distributer = distributer;
	}


	public String getZemljaPorekla() {
		return zemljaPorekla;
	}


	public void setZemljaPorekla(String zemljaPorekla) {
		this.zemljaPorekla = zemljaPorekla;
	}


	public int getGodinaProizvodnje() {
		return godinaProizvodnje;
	}


	public void setGodinaProizvodnje(int godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}


	public String getOpis() {
		return opis;
	}


	public void setOpis(String opis) {
		this.opis = opis;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", naziv=" + naziv + ", reziser=" + reziser + ", glumci=" + glumci + ", zanrovi="
				+ zanrovi + ", trajanje=" + trajanje + ", distributer=" + distributer + ", zemljaPorekla="
				+ zemljaPorekla + ", godinaProizvodnje=" + godinaProizvodnje + ", opis=" + opis + ", obrisan=" + obrisan
				+ "]";
	}
}
