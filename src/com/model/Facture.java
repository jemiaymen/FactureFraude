package com.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the FACTURE database table.
 * 
 */
@Entity
@NamedQuery(name="Facture.findAll", query="SELECT f FROM Facture f")
public class Facture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String detailconso;

	private String district;

	private String montan;

	private long nbrmois;

	private long nservice;

	private String rib;

	private String rip;

	private String sold;

	@Column(name="\"STATE\"")
	private long state;

	private String taxe;

	private String totalconso;

	private String typeservice;

	//bi-directional many-to-one association to Compteur
	@ManyToOne
	@JoinColumn(name="CID")
	private Compteur compteur;

	public Facture() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDetailconso() {
		return this.detailconso;
	}

	public void setDetailconso(String detailconso) {
		this.detailconso = detailconso;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getMontan() {
		return this.montan;
	}

	public void setMontan(String montan) {
		this.montan = montan;
	}

	public long getNbrmois() {
		return this.nbrmois;
	}

	public void setNbrmois(long nbrmois) {
		this.nbrmois = nbrmois;
	}

	public long getNservice() {
		return this.nservice;
	}

	public void setNservice(long nservice) {
		this.nservice = nservice;
	}

	public String getRib() {
		return this.rib;
	}

	public void setRib(String rib) {
		this.rib = rib;
	}

	public String getRip() {
		return this.rip;
	}

	public void setRip(String rip) {
		this.rip = rip;
	}

	public String getSold() {
		return this.sold;
	}

	public void setSold(String sold) {
		this.sold = sold;
	}

	public long getState() {
		return this.state;
	}

	public void setState(long state) {
		this.state = state;
	}

	public String getTaxe() {
		return this.taxe;
	}

	public void setTaxe(String taxe) {
		this.taxe = taxe;
	}

	public String getTotalconso() {
		return this.totalconso;
	}

	public void setTotalconso(String totalconso) {
		this.totalconso = totalconso;
	}

	public String getTypeservice() {
		return this.typeservice;
	}

	public void setTypeservice(String typeservice) {
		this.typeservice = typeservice;
	}

	public Compteur getCompteur() {
		return this.compteur;
	}

	public void setCompteur(Compteur compteur) {
		this.compteur = compteur;
	}

}