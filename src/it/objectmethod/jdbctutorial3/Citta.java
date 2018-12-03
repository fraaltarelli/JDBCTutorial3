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


public class Citta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
   String countrycode;
   String countryCodeSalvato;
   String currentNation, nomeNazioneSelezionata;
   boolean newcitta;
   int currentCityId;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		IDaoCity daoCity= new DaoCityImpl();
		IDaoNation daoNation= new DaoNationImpl();
		currentCityId=0;
		
		if (request.getParameter("newcitta")!=null) {
			newcitta=true;
			}
		else {
			newcitta=false;
		}
		
		
		if(request.getParameter("codiceNazioneSelezionata")!= null) {
		countrycode= request.getParameter("codiceNazioneSelezionata");
		countryCodeSalvato =countrycode;
		}
		else {
			countrycode= null;
		}
		
		
		
		if(request.getParameter("nomeNazioneSelezionata")!= null) {
			currentNation= request.getParameter("nomeNazioneSelezionata");
			nomeNazioneSelezionata =currentNation;
			}
			else {
				currentNation= null;
			}
		
		
		if(request.getParameter("idcitta")!=null) {
		currentCityId= Integer.parseInt(request.getParameter("idcitta"));
		
		}
		
		List<City> lista= daoCity.getAllCitiesByNation(countryCodeSalvato);
		session.setAttribute("listaNazioni", null);
		session.setAttribute("currentNation", nomeNazioneSelezionata);
		session.setAttribute("currentCityId", currentCityId);
		request.setAttribute("newcitta", newcitta);
		session.setAttribute("currentList", "Citta");
		session.setAttribute("targetServletBottone", "runNazioni");
		session.setAttribute("countryCodeSalvato", countryCodeSalvato);
		session.setAttribute("listaCitta", lista);
		request.getRequestDispatcher("inizio.jsp").forward(request, response);
		
	}

	

}
