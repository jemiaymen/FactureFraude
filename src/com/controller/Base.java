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
	public long _id ;
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
	public long getId(HttpServletRequest request){
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
					return Long.parseLong(uid);
				}catch(Exception ex){
				}
			}
			
		}
		return 0;
	}
	
	public void IsLogin(HttpServletRequest request, HttpServletResponse response,String poste,String url)
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
						long id = Long.parseLong(uid);
						Utilisateur u = (Utilisateur) em.find(Utilisateur.class,id);
						String p = u.getPoste();
						if(p.equals(poste)){
							request.setAttribute("user", u);
							request.getRequestDispatcher(url).forward(request, response);
						}else {
							response.sendRedirect("Logout");
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
