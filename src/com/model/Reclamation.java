package com.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RECLAMATION database table.
 * 
 */
@Entity
@NamedQuery(name="Reclamation.findAll", query="SELECT r FROM Reclamation r")
public class Reclamation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private long etat;

	private String libelle;

	private String typerec;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="USERID")
	private Utilisateur utilisateur;

	public Reclamation() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEtat() {
		return this.etat;
	}

	public void setEtat(long etat) {
		this.etat = etat;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getTyperec() {
		return this.typerec;
	}

	public void setTyperec(String typerec) {
		this.typerec = typerec;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}