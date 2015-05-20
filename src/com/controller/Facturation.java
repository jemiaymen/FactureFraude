package com.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Compteur;
import com.model.Facture;
import com.model.Utilisateur;
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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long cid,mod,comp,print;
		
		try {
			cid = Long.parseLong(request.getParameter("cid"));
		} catch (NumberFormatException e) {
			cid = 0;
		}
		
		
		try {
			mod = Long.parseLong(request.getParameter("mod"));
		} catch (NumberFormatException e) {
			mod = 0;
		}
		
		try {
			print = Long.parseLong(request.getParameter("print"));
		} catch (NumberFormatException e) {
			print = 0;
		}
		
		try {
			comp = Long.parseLong(request.getParameter("comp"));
		} catch (NumberFormatException e) {
			comp = 0;
		}
		
		if(comp != 0){
			PasserServiceContable(comp);
		}
		
		if(cid != 0){
			request.setAttribute("c", getCompteur(cid));
		}
		
		if(mod != 0){
			request.setAttribute("fact", getFacture(mod));
		}
		
		
		if(print != 0){
			request.setAttribute("printsrc", "$('#printc"+ print +"').print();");
		}
		
		request.setAttribute("fct",  getAllFacture());
		
		IsLogin(request,response,"agent","Facturation.jsp");
	}

	public Facture getFacture(long mod) {
		EntityManager em = emf.createEntityManager();
		try{
			return em.find(Facture.class, mod);
		}catch(Exception ex){
			return null;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tsr = request.getParameter("tsr");
		String dsrt = request.getParameter("dsrt");
		String fadd = request.getParameter("fadd");
		
		
		long nsr,nm,rip,rib,dconso,tconso,taxe,sold,mnt,cid,fid;
	
		
		try {
			nsr = Long.parseLong(request.getParameter("nsr"));
			nm = Long.parseLong(request.getParameter("nm"));
			rip = Long.parseLong(request.getParameter("rip"));
			rib = Long.parseLong(request.getParameter("rib"));
			dconso = Long.parseLong(request.getParameter("dconso"));
			tconso = Long.parseLong(request.getParameter("tconso"));
			taxe = Long.parseLong(request.getParameter("taxe"));
			sold = Long.parseLong(request.getParameter("sold"));
			mnt = Long.parseLong(request.getParameter("mnt"));
			cid = Long.parseLong(request.getParameter("cid"));
		} catch (NumberFormatException e) {
			nsr = 0;
			nm = 0;
			rip = 0;
			rib = 0;
			dconso = 0;
			tconso = 0;
			taxe = 0;
			sold = 0;
			mnt = 0;
			cid = 0;
		}
		
		try {
			fid = Long.parseLong(request.getParameter("fid"));
		}catch(Exception e){
			fid = 0;
		}
		
		if (nsr != 0 && nm != 0 && rip != 0 && rib != 0 && dconso != 0 &&
			tconso != 0 && taxe != 0 && sold != 0 && mnt != 0 && cid != 0 &&
			tsr != null && dsrt != null && fadd != null){
			
			Compteur c = getCompteur(cid);
			
			
			Facture tmp = new Facture();
			tmp.setCompteur(c);
			tmp.setTypeservice(tsr);
			tmp.setTotalconso(tconso + "");
			tmp.setTaxe(taxe+ "");
			
			tmp.setDetailconso(dconso + "");
			tmp.setDistrict(dsrt);
			tmp.setMontan(mnt + "");
			tmp.setNbrmois( nm);
			tmp.setNservice(nsr);
			tmp.setRib(rib + "");
			tmp.setRip(rip +"");
			tmp.setSold(sold +"");
			tmp.setState(1);
			
			AddFacture(tmp);
			
		}else if (nsr != 0 && nm != 0 && rip != 0 && rib != 0 && dconso != 0 &&
				tconso != 0 && taxe != 0 && sold != 0 && mnt != 0 && cid != 0 &&
				tsr != null && dsrt != null && fid != 0){
			
			Compteur c = getCompteur(cid);
			Facture tmp = new Facture();
			tmp.setCompteur(c);
			tmp.setTypeservice(tsr);
			tmp.setTotalconso(tconso + "");
			tmp.setTaxe(taxe+ "");
			
			tmp.setDetailconso(dconso + "");
			tmp.setDistrict(dsrt);
			tmp.setMontan(mnt + "");
			tmp.setNbrmois( nm);
			tmp.setNservice(nsr);
			tmp.setRib(rib + "");
			tmp.setRip(rip +"");
			tmp.setSold(sold +"");
			tmp.setState(1);
			UpdateFacture(fid,tmp);
			
		}
		
		request.setAttribute("fct",  getAllFacture());
		
		IsLogin(request,response,"agent","Facturation.jsp");
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
	
	
	public boolean UpdateFacture(long fid,Facture f){
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
	
	public Compteur getCompteur(long uid){
		EntityManager em = emf.createEntityManager();
		try{
			return em.find(Compteur.class, uid);
		}catch(Exception ex){
			return null;
		}	
	}
	
	public boolean PasserServiceContable(long fid){
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			Facture tmp = em.find(Facture.class, fid);
			tmp.setState(2);
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
