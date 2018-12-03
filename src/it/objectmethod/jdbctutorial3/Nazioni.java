package it.objectmethod.jdbctutorial3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.objectmethod.jdbctutorial3.dao.IDaoCity;
import it.objectmethod.jdbctutorial3.dao.IDaoNation;
import it.objectmethod.jdbctutorial3.dao.impl.DaoCityImpl;
import it.objectmethod.jdbctutorial3.dao.impl.DaoNationImpl;
import it.objectmethod.jdbctutorial3.model.City;
import it.objectmethod.jdbctutorial3.model.Nation;


public class Nazioni extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	  String continenteSelezionato=null;   
	  boolean newcitta;
	  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		IDaoNation daoNation= new DaoNationImpl();
		IDaoCity daoCity= new DaoCityImpl();
		
		if (request.getParameter("newcitta")!=null) {
			newcitta=true;
			}
		else {
			newcitta=false;
		}
		
		
		
		if (request.getParameter("idContinente")!=null) {
			continenteSelezionato = request.getParameter("idContinente");
		}
		else {
			String continenteBySession=(String)session.getAttribute("continenteBySession");
			continenteSelezionato=continenteBySession;
		}
		
		
		
		session.setAttribute("continenteBySession", continenteSelezionato);
		List<Nation> lista= daoNation.getAllNationsByContinent(continenteSelezionato);
		session.setAttribute("listaContinenti", null);
		session.setAttribute("listaNazioni", lista);
		session.setAttribute("listaCitta", null);
		request.setAttribute("newcitta", newcitta);
		session.setAttribute("targetServletBottone", "runContinenti");
		session.setAttribute("currentNation", null);
		session.setAttribute("currentList", "");
		session.setAttribute("currentCityId", 0);
		request.getRequestDispatcher("inizio.jsp").forward(request, response);
		
		
	}

	

}
