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
public class Campo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	private Long lunghezza;
	
	private Long larghezza;
	
	@OneToMany
	@JoinColumn(name = "campo_id")
	private List<Utente> utenti;
	
	@OneToMany
	@JoinColumn(name = "campo_id")
	private List<Prenotazione> prenotazioni;

	public Campo() {
	}

	public Campo(String nome, Long lunghezza, Long larghezza, List<Utente> utenti, List<Prenotazione> prenotazioni) {
		this.nome = nome;
		this.lunghezza = lunghezza;
		this.larghezza = larghezza;
		this.utenti = utenti;
		this.prenotazioni = prenotazioni;
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

	public Long getLunghezza() {
		return lunghezza;
	}

	public void setLunghezza(Long lunghezza) {
		this.lunghezza = lunghezza;
	}

	public Long getLarghezza() {
		return larghezza;
	}

	public void setLarghezza(Long larghezza) {
		this.larghezza = larghezza;
	}

	public List<Utente> getUtenti() {
		return utenti;
	}

	public void setUtenti(List<Utente> utenti) {
		this.utenti = utenti;
	}

	public List<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

	public void setPrenotazioni(List<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}
}