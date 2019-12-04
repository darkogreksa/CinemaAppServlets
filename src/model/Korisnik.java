package model;

public class Korisnik {
	
	public enum Role {
		USER,
		ADMIN
	};
	
	private String username;
	private String password;
	private String datumRegistracije;
	private Role role;
	
	
	public Korisnik(String username, String password, String datumRegistracije, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.datumRegistracije = datumRegistracije;
		this.role = role;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getDatumRegistracije() {
		return datumRegistracije;
	}


	public void setDatumRegistracije(String datumRegistracije) {
		this.datumRegistracije = datumRegistracije;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "Korisnik [username=" + username + ", password=" + password + ", datumRegistracije=" + datumRegistracije
				+ ", role=" + role + "]";
	}
	
	

}
