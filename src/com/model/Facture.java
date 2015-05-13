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

	private BigDecimal nbrmois;

	private BigDecimal nservice;

	private String rib;

	private String rip;

	private String sold;

	private String taxe;

	private String totalconso;

	private String typeservice;

	private BigDecimal userid;

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

	public BigDecimal getNbrmois() {
		return this.nbrmois;
	}

	public void setNbrmois(BigDecimal nbrmois) {
		this.nbrmois = nbrmois;
	}

	public BigDecimal getNservice() {
		return this.nservice;
	}

	public void setNservice(BigDecimal nservice) {
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

	public BigDecimal getUserid() {
		return this.userid;
	}

	public void setUserid(BigDecimal userid) {
		this.userid = userid;
	}

	public Compteur getCompteur() {
		return this.compteur;
	}

	public void setCompteur(Compteur compteur) {
		this.compteur = compteur;
	}

}