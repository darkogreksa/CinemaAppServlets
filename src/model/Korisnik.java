package model;

import java.util.Date;;

public class Korisnik {
	
	public enum Role {
		USER,
		ADMIN
	};
	
	private String username;
	private String password;
	private Date datumRegistracije;
	private Role role;
	
	
	public Korisnik(String username, String password, Date datumRegistracije, Role role) {
		this.username = username;
		this.password = password;
		this.datumRegistracije = datumRegistracije;
		this.role = role;
	}
	
	public Korisnik(String username) {
		this.username = username;
	}
	
	public Korisnik(String username, String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	

	public Korisnik(String username, Date datumRegistracije, Role role) {
		super();
		this.username = username;
		this.datumRegistracije = datumRegistracije;
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		return prime + username.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;

		Korisnik other = (Korisnik) obj;
		return username.equals(other.username);
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


	public Date getDatumRegistracije() {
		return datumRegistracije;
	}


	public void setDatumRegistracije(Date datumRegistracije) {
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
