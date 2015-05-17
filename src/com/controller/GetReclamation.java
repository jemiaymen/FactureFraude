package com.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Reclamation;

/**
 * Servlet implementation class GetReclamation
 */
@WebServlet("/GetReclamation")
public class GetReclamation extends Base {
	private static final long serialVersionUID = 1L;

    public GetReclamation() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id =0;
		long ver = 0;
		
		try {
			id = Long.parseLong(request.getParameter("del"));
		} catch (Exception ex) {
			id = 0;
		}
		
		
		try {
			ver = Long.parseLong(request.getParameter("ver"));
		} catch (Exception ex) {
			ver = 0;
		}
		
		if(id != 0){
			ClotureReclamation(id);
		}
		
		
		if(ver != 0){
			request.setAttribute("rec", getRec(ver));
		}
		
		
		request.setAttribute("recs", getReclamation());
		IsLogin(request,response,"agent","Reclamation.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	public List<Reclamation> getReclamation(){
			EntityManager em = emf.createEntityManager();
			try{
				return em.createQuery("select r from Reclamation r where r.etat = 1", Reclamation.class).getResultList();
			}catch(Exception ex){
				return null;
			}	
	}
	
	public boolean ClotureReclamation(long rid ){
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			Reclamation r = em.find(Reclamation.class, rid);
			r.setEtat(2);
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
	
	
	public Reclamation getRec(long rid){
		EntityManager em = emf.createEntityManager();
		try{
			return em.find(Reclamation.class, rid);
		}catch(Exception ex){
			return null;
		}
	}
}
