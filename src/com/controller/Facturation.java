package com.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Facture;
/**
 * Servlet implementation class Facturation
 */
@WebServlet("/Facturation")
public class Facturation extends Base {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see Base#Base()
     */
    public Facturation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public boolean AddFacture(Facture f){
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			em.persist(f);
			em.getTransaction().commit();
			em.close();
			return true;
		}catch(Exception ex){
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
				em.close();
			}
			return false;
		}	
	}
	
	
	public boolean UpdateFacture(int fid,Facture f){
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			Facture tmp = em.find(Facture.class, fid);
			tmp.setTypeservice(f.getTypeservice());
			tmp.setTotalconso(f.getTotalconso());
			tmp.setTaxe(f.getTaxe());
			tmp.setCompteur(f.getCompteur());
			tmp.setDetailconso(f.getDetailconso());
			tmp.setDistrict(f.getDistrict());
			tmp.setMontan(f.getMontan());
			tmp.setNbrmois(f.getNbrmois());
			tmp.setNservice(f.getNservice());
			tmp.setRib(f.getRib());
			tmp.setRip(f.getRip());
			tmp.setSold(f.getSold());
			em.getTransaction().commit();
			em.close();
			return true;
		}catch(Exception ex){
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
				em.close();
			}
			return false;
		}	
	}
	
	
	
	public boolean PasserServiceContable(int fid){
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			Facture tmp = em.find(Facture.class, fid);
			tmp.setState(3);
			em.getTransaction().commit();
			em.close();
			return true;
		}catch(Exception ex){
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
				em.close();
			}
			return false;
		}
	}
	
	public List<Facture> getAllFacture(){
		EntityManager em = emf.createEntityManager();
		try{
			return em.createQuery("select f from Facture f where f.state = 1",Facture.class).getResultList();
		}catch(Exception ex){
			return null;
		}
	}
}
