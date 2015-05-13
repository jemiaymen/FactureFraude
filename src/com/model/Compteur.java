package com.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the COMPTEUR database table.
 * 
 */
@Entity
@NamedQuery(name="Compteur.findAll", query="SELECT c FROM Compteur c")
public class Compteur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Temporal(TemporalType.DATE)
	private Date dataactivation;

	private String marque;

	@Column(name="\"TYPE\"")
	private String type;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="USERID")
	private Utilisateur utilisateur;

	//bi-directional many-to-one association to Consomation
	@OneToMany(mappedBy="compteur")
	private List<Consomation> consomations;

	//bi-directional many-to-one association to Facture
	@OneToMany(mappedBy="compteur")
	private List<Facture> factures;

	public Compteur() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataactivation() {
		return this.dataactivation;
	}

	public void setDataactivation(Date dataactivation) {
		this.dataactivation = dataactivation;
	}

	public String getMarque() {
		return this.marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Consomation> getConsomations() {
		return this.consomations;
	}

	public void setConsomations(List<Consomation> consomations) {
		this.consomations = consomations;
	}

	public Consomation addConsomation(Consomation consomation) {
		getConsomations().add(consomation);
		consomation.setCompteur(this);

		return consomation;
	}

	public Consomation removeConsomation(Consomation consomation) {
		getConsomations().remove(consomation);
		consomation.setCompteur(null);

		return consomation;
	}

	public List<Facture> getFactures() {
		return this.factures;
	}

	public void setFactures(List<Facture> factures) {
		this.factures = factures;
	}

	public Facture addFacture(Facture facture) {
		getFactures().add(facture);
		facture.setCompteur(this);

		return facture;
	}

	public Facture removeFacture(Facture facture) {
		getFactures().remove(facture);
		facture.setCompteur(null);

		return facture;
	}

}