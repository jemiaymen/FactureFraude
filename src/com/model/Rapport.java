package com.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RAPPORT database table.
 * 
 */
@Entity
@NamedQuery(name="Rapport.findAll", query="SELECT r FROM Rapport r")
public class Rapport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String libelle;

	private String typerapport;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="USERID")
	private Utilisateur utilisateur;

	public Rapport() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getTyperapport() {
		return this.typerapport;
	}

	public void setTyperapport(String typerapport) {
		this.typerapport = typerapport;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}