package it.objectmethod.jdbctutorial3.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.objectmethod.jdbctutorial3.dao.IDaoCity;
import it.objectmethod.jdbctutorial3.dao.impl.DaoCityImpl;
import it.objectmethod.jdbctutorial3.model.City;


public class CercaCitta extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	int currentCityId;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		IDaoCity daoCitta= new DaoCityImpl();
		String cercacitta= request.getParameter("cercacitta");
		if (request.getParameter("creacitta")!=null) {
			currentCityId=0;
		}
		List<City> lista= daoCitta.ritornaCitta(cercacitta);
		request.setAttribute("ritornaCitta", lista);
		session.setAttribute("currentCityId", currentCityId);
		session.setAttribute("currentList", "CercaCitta");
		//session.setAttribute("currentServlet", "CercaCitta");
		request.getRequestDispatcher("inizio.jsp").forward(request, response);

	}

}
