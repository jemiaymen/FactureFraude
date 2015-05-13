package com.controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.*;


public class Base extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("FactureFraude");   
	public int _id ;
	public String _poste;
    public Base() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		_id = getId(request);
		_poste = getPoste();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public String getPoste(){
		EntityManager em = emf.createEntityManager();
		try {
			Utilisateur  u = em.find(Utilisateur.class,_id);
			return u.getPoste();		
		} catch (Exception e) {
			return "";
		}
	}
	public int getId(HttpServletRequest request){
		Cookie[] c = request.getCookies();
		String uid = null;
		if (c != null) {
			for (Cookie cookie : c) {
				if (cookie.getName().equals("uid")) {
					uid = cookie.getValue();
				}
			}
			if (uid != null) {
				try {
					return Integer.parseInt(uid);
				}catch(Exception ex){
				}
			}
			
		}
		return 0;
	}
	
	public void IsLogin(HttpServletRequest request, HttpServletResponse response,String poste)
			throws IOException, ServletException {
		String uid = null;
		EntityManager em = emf.createEntityManager();

		Cookie[] c = request.getCookies();
		if (c != null) {
			for (Cookie cookie : c) {
				if (cookie.getName().equals("uid")) {
					uid = cookie.getValue();
				}
			}
			if (uid != null) {
				try {
					int id = Integer.parseInt(uid);
					Utilisateur u = (Utilisateur) em.find(Utilisateur.class, id);
					if (u == null) {
						response.sendRedirect("Logout");
					} else {
						if(u.getPoste().equals(poste)){
							request.setAttribute("user", u);
						}else {
							response.sendRedirect("Logout");
						}
						
					}
				} catch (Exception ex) {
					response.sendRedirect("Logout");
				}

			} else {
				response.sendRedirect("Logout");
			}
		} else {
			response.sendRedirect("Logout");
		}

	}
}
