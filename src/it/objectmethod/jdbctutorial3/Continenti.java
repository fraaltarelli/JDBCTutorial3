package it.objectmethod.jdbctutorial3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.objectmethod.jdbctutorial3.dao.IDaoNation;
import it.objectmethod.jdbctutorial3.dao.impl.DaoNationImpl;


public class Continenti extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		IDaoNation daoNation= new DaoNationImpl();
		
		List<String> lista= daoNation.getAllContinents();
		session.setAttribute("listaNazioni", null);
		session.setAttribute("listaContinenti", lista);
		request.setAttribute("newcitta", false);
		session.setAttribute("targetServletBottone", "runContinenti");
		session.setAttribute("currentList", "");
		session.setAttribute("currentCityId", 0);	
		request.getRequestDispatcher("inizio.jsp").forward(request, response);
		
	}

	

}
