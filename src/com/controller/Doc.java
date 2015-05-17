package com.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Dossier;


/**
 * Servlet implementation class Doc
 */
@WebServlet("/Doc")
public class Doc extends Base {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see Base#Base()
     */
    public Doc() {
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

	
	public List<Dossier> getAllDossier(){
		EntityManager em = emf.createEntityManager();
		try{
			return em.createQuery("select d from Dossier d where d.etat = 1", Dossier.class).getResultList();
		}catch(Exception ex){
			return null;
		}	
	}
	
	public boolean updateDossier(int did,String des){
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			Dossier d = em.find(Dossier.class, did);
			d.setDes(des);
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
	
}
