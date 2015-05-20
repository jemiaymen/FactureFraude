package com.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.*;



@WebServlet("/CalculFraude")
public class CalculFraude extends Base {
	private static final long serialVersionUID = 1L;
       

    public CalculFraude() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long uid,suiv,calc;
		
		try {
			uid = Long.parseLong(request.getParameter("suiv"));
		} catch (Exception ex) {
			uid = 0;
		}
		
		
		try {
			suiv = Long.parseLong(request.getParameter("suivc"));
		} catch (Exception ex) {
			suiv = 0;
		}
		
		
		try {
			calc = Long.parseLong(request.getParameter("calc"));
		} catch (Exception ex) {
			calc = 0;
		}
		
		if(uid !=0){
			
			request.setAttribute("cs", getCompteur(uid));
		}
		if(suiv != 0){
			request.setAttribute("conso", getConsomation(suiv));
		}
		
		if(calc != 0){
			request.setAttribute("tau", Calcul(calc));
		}
		//else{
		//	response.sendRedirect("home");
		//}
		
		IsLogin(request,response,"agent","FraudeCalcule.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
	public String Calcul(long consid){
		EntityManager em = emf.createEntityManager();
		try{
			Consomation c1 = em.find(Consomation.class, consid);
			long year = Long.parseLong(c1.getAnne()) - 1;
			Consomation c2 = em.createQuery("select c from Consomation c where c.anne =" + year ,Consomation.class).getSingleResult();
			float tau = (Float.parseFloat(c2.getConsoannuel()) / Float.parseFloat(c1.getConsoannuel()) ) * 100 ;
			return "Le Taux des annes " + c1.getAnne() + " / " + c2.getAnne() + " est : <b> " + tau + "</b>";
		}catch(Exception ex){
			return "Requette non Conforme !";
		}
	}
	
	public List<Compteur> getCompteur(long uid){
		EntityManager em = emf.createEntityManager();
		try{
			Utilisateur u = em.find(Utilisateur.class, uid);
			return u.getCompteurs();
		}catch(Exception ex){
			return null;
		}	
	}

	public List<Consomation> getConsomation(long cid){
		EntityManager em = emf.createEntityManager();
		try{
			Compteur c = em.find(Compteur.class, cid);
			return c.getConsomations();
		}catch(Exception ex){
			return null;
		}	
	}
}
