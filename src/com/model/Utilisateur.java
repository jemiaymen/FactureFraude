package com.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the UTILISATEUR database table.
 * 
 */
@Entity
@NamedQuery(name="Utilisateur.findAll", query="SELECT u FROM Utilisateur u")
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String adress;

	private String cin;

	private String nom;

	private String poste;

	private String prenom;

	private String pw;

	private String tel;

	//bi-directional many-to-one association to Compteur
	@OneToMany(mappedBy="utilisateur")
	private List<Compteur> compteurs;

	//bi-directional many-to-one association to Dossier
	@OneToMany(mappedBy="utilisateur")
	private List<Dossier> dossiers;

	//bi-directional many-to-one association to Rapport
	@OneToMany(mappedBy="utilisateur")
	private List<Rapport> rapports;

	//bi-directional many-to-one association to Reclamation
	@OneToMany(mappedBy="utilisateur")
	private List<Reclamation> reclamations;

	public Utilisateur() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getCin() {
		return this.cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPoste() {
		return this.poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPw() {
		return this.pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public List<Compteur> getCompteurs() {
		return this.compteurs;
	}

	public void setCompteurs(List<Compteur> compteurs) {
		this.compteurs = compteurs;
	}

	public Compteur addCompteur(Compteur compteur) {
		getCompteurs().add(compteur);
		compteur.setUtilisateur(this);

		return compteur;
	}

	public Compteur removeCompteur(Compteur compteur) {
		getCompteurs().remove(compteur);
		compteur.setUtilisateur(null);

		return compteur;
	}

	public List<Dossier> getDossiers() {
		return this.dossiers;
	}

	public void setDossiers(List<Dossier> dossiers) {
		this.dossiers = dossiers;
	}

	public Dossier addDossier(Dossier dossier) {
		getDossiers().add(dossier);
		dossier.setUtilisateur(this);

		return dossier;
	}

	public Dossier removeDossier(Dossier dossier) {
		getDossiers().remove(dossier);
		dossier.setUtilisateur(null);

		return dossier;
	}

	public List<Rapport> getRapports() {
		return this.rapports;
	}

	public void setRapports(List<Rapport> rapports) {
		this.rapports = rapports;
	}

	public Rapport addRapport(Rapport rapport) {
		getRapports().add(rapport);
		rapport.setUtilisateur(this);

		return rapport;
	}

	public Rapport removeRapport(Rapport rapport) {
		getRapports().remove(rapport);
		rapport.setUtilisateur(null);

		return rapport;
	}

	public List<Reclamation> getReclamations() {
		return this.reclamations;
	}

	public void setReclamations(List<Reclamation> reclamations) {
		this.reclamations = reclamations;
	}

	public Reclamation addReclamation(Reclamation reclamation) {
		getReclamations().add(reclamation);
		reclamation.setUtilisateur(this);

		return reclamation;
	}

	public Reclamation removeReclamation(Reclamation reclamation) {
		getReclamations().remove(reclamation);
		reclamation.setUtilisateur(null);

		return reclamation;
	}

}