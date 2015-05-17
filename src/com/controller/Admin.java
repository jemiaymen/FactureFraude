package com.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
		
		request.setAttribute("users", getAllUser());
		IsLogin(request,response,"admin","Admin.jsp");

		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String adress = request.getParameter("adress");
		
		String cin = request.getParameter("cin");
		
		String nom = request.getParameter("nom");
		
		String prenom = request.getParameter("prenom");
		
		String poste = request.getParameter("poste");
		
		String pw = request.getParameter("pw");
		
		String tel = request.getParameter("tel");
		
		
		if(adress != null && cin != null && nom != null && prenom != null
				&& poste != null && pw != null && tel != null){
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
		
		request.setAttribute("users", getAllUser());
		IsLogin(request,response,"admin","Admin.jsp");
	}

	
	public boolean delUser(long id){
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			Query q = em.createQuery("Delete Utilisateur where id =" + id);
			q.executeUpdate();
			em.getTransaction().commit();
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
	
	
	public List<Utilisateur> getAllUser(){
		EntityManager em = emf.createEntityManager();
		try{
			return em.createQuery("select u from Utilisateur u ", Utilisateur.class).getResultList();
		}catch(Exception ex){
			return null;
		}
		
	}
	
}
