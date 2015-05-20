package com.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Dossier;
import com.model.Utilisateur;


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
		
		long uid,mod ;
		
		try{
			uid = Long.parseLong(request.getParameter("uid"));
		}catch(Exception ex){
			uid = 0;
		}
		
		
		try{
			mod = Long.parseLong(request.getParameter("mod"));
		}catch(Exception ex){
			mod = 0;
		}
		
		if(mod != 0){
			request.setAttribute("doc", getDoc(mod));
		}
		
		if(uid != 0){
			request.setAttribute("usr", getUser(uid));
		}
		request.setAttribute("docs",getAllDossier());
		IsLogin(request,response,"agent","Dossier.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String des = request.getParameter("des");
		
		long uid,mdid;
		
		try{
			uid = Long.parseLong(request.getParameter("uid"));
		}catch(Exception ex){
			uid = 0;
		}
		
		try{
			mdid = Long.parseLong(request.getParameter("mdid"));
		}catch(Exception ex){
			mdid = 0;
		}
		
		if(des != null && uid != 0){
			Dossier d = new Dossier();
			
			d.setDes(des);
			d.setEtat(1);
			d.setUtilisateur(getUser(uid));
			AddDossier(d);
		}
		
		if(des != null && mdid != 0){
			updateDossier(mdid,des);
		}
		
		request.setAttribute("docs",getAllDossier());
		IsLogin(request,response,"agent","Dossier.jsp");
	}

	
	public List<Dossier> getAllDossier(){
		EntityManager em = emf.createEntityManager();
		try{
			return em.createQuery("select d from Dossier d where d.etat = 1", Dossier.class).getResultList();
		}catch(Exception ex){
			return null;
		}	
	}
	
	public boolean updateDossier(long did,String des){
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
	
	public Utilisateur getUser(long uid){
		EntityManager em = emf.createEntityManager();
		try{
			return em.find(Utilisateur.class,uid);
		}catch(Exception ex){
			return null;
		}	
	}
	
	
	public Dossier getDoc(long did){
		EntityManager em = emf.createEntityManager();
		try{
			return em.find(Dossier.class,did);
		}catch(Exception ex){
			return null;
		}	
	}
	
	
	public boolean AddDossier(Dossier d){
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			em.persist(d);
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
