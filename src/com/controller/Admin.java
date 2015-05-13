package com.controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Utilisateur;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends Base implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see Base#Base()
     */
    public Admin() {
        super();
 
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IsLogin(request,response,"Admin");
		
		int del;
		
		try {
			del = Integer.parseInt(request.getParameter("del")) ;
		}catch(Exception ex){
			del = 0;
		}
		
		
		if(del != 0 ){
			if(delUser(del)){
				
			}
		}
		

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IsLogin(request,response,"Admin");
		
		String conf = request.getParameter("add");
		
		String adress = request.getParameter("adress");
		
		String cin = request.getParameter("cin");
		
		String nom = request.getParameter("nom");
		
		String prenom = request.getParameter("prenom");
		
		String poste = request.getParameter("poste");
		
		String pw = request.getParameter("pw");
		
		String tel = request.getParameter("tel");
		
		
		if(conf != null && adress != null && cin != null && nom != null && prenom != null
				&& poste != null && poste != null && pw != null && tel != null){
			Utilisateur u = new Utilisateur();
			
			u.setAdress(adress);
			u.setCin(cin);
			u.setNom(nom);
			u.setPrenom(prenom);
			u.setPoste(poste);
			u.setPw(pw);
			u.setTel(tel);
			
			
			if(addUser(u)){
				
			}else {
				
			}
			
		}
	}

	
	public boolean delUser(int id){
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			Utilisateur u = em.find(Utilisateur.class, id);
			em.remove(u);
			em.getTransaction().commit();
			em.close();
			return true;
		}catch(Exception ex){
			if (em.getTransaction().isActive()){
				em.getTransaction().rollback();
				em.close();
			}
		}
		
		return false;
	}
	
	
	public boolean addUser(Utilisateur u ){
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
			em.close();
			return true;
		}catch(Exception ex){
			if (em.getTransaction().isActive()){
				em.getTransaction().rollback();
				em.close();
			}
		}
		
		return false;
	}
}
