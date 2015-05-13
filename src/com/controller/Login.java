package com.controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.*;

@WebServlet("/Login")
public class Login extends Base {
	private static final long serialVersionUID = 1L;


	public Login() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/Login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String pw = request.getParameter("pw");
		
		if(login != null && pw != null){
			if (!login(login, pw, response)) {
				request.getRequestDispatcher("/Login.jsp?error=yes").forward(
						request, response);
			} else {

				response.sendRedirect("home");

			}
		}

	}

	public boolean login(String mat, String pw, HttpServletResponse response) {
		EntityManager em = emf.createEntityManager();
		try {
			Utilisateur u = em.createQuery(
					"SELECT u FROM Utilisateur u WHERE u.cin = '" + mat
							+ "' AND u.pw ='" + pw + "' ", Utilisateur.class)
					.getSingleResult();
			if (u != null) {
				long uid = u.getId();
				Cookie c = new Cookie("uid", uid + "");
				c.setMaxAge(3600);
				response.addCookie(c);
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

}
