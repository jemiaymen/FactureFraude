package com.model;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the DOSSIER database table.
 * 
 */
@Entity
@NamedQuery(name="Dossier.findAll", query="SELECT d FROM Dossier d")
public class Dossier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String des;

	private long etat;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="USERID")
	private Utilisateur utilisateur;

	public Dossier() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public long getEtat() {
		return this.etat;
	}

	public void setEtat(long etat) {
		this.etat = etat;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}