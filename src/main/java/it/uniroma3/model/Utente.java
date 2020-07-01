package it.uniroma3.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String cognome;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String role;
	
	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@OneToMany
	@JoinColumn(name = "utente_id")
	private List <Prenotazione> prenotazioni;

	@OneToMany
	@JoinColumn(name = "utente_id")
	private List<Pagamento> pagamenti;

	public Utente() {
	}

	public Utente(Long id, String nome, String cognome, String email, String password,
			List<Prenotazione> prenotazioni, List<Pagamento> pagamenti , String role) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.role=role;
		this.password = password;
		this.prenotazioni  = prenotazioni;
		this.pagamenti = pagamenti;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
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

	public List<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

	public void setPrenotazioni(List<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}

	public List<Pagamento> getPagamenti() {
		return pagamenti;
	}

	public void setPagamenti(List<Pagamento> pagamenti) {
		this.pagamenti = pagamenti;
	}
}