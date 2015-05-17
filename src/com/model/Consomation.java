package com.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CONSOMATION database table.
 * 
 */
@Entity
@NamedQuery(name="Consomation.findAll", query="SELECT c FROM Consomation c")
public class Consomation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String anne;

	private String consoannuel;

	private String consocompteur;

	//bi-directional many-to-one association to Compteur
	@ManyToOne
	@JoinColumn(name="CID")
	private Compteur compteur;

	public Consomation() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAnne() {
		return this.anne;
	}

	public void setAnne(String anne) {
		this.anne = anne;
	}

	public String getConsoannuel() {
		return this.consoannuel;
	}

	public void setConsoannuel(String consoannuel) {
		this.consoannuel = consoannuel;
	}

	public String getConsocompteur() {
		return this.consocompteur;
	}

	public void setConsocompteur(String consocompteur) {
		this.consocompteur = consocompteur;
	}

	public Compteur getCompteur() {
		return this.compteur;
	}

	public void setCompteur(Compteur compteur) {
		this.compteur = compteur;
	}

}