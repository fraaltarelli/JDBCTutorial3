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
import it.objectmethod.jdbctutorial3.dao.IDaoNation;
import it.objectmethod.jdbctutorial3.dao.impl.DaoCityImpl;
import it.objectmethod.jdbctutorial3.dao.impl.DaoNationImpl;
import it.objectmethod.jdbctutorial3.model.City;


public class SaveCitta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	boolean newcitta;
	City cittaDaModificare;
	String nomeDaSalvare, distrettoDaSalvare, nazioneSelezionata;
	int popolazioneDaSalvare;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		IDaoCity daoCity= new DaoCityImpl();
		IDaoNation daoNation= new DaoNationImpl();
		List<City> lista=null;
		int currentCityId=(Integer)session.getAttribute("currentCityId");
		String currentNation= (String)session.getAttribute("currentnation");
		
		String currentList= (String)session.getAttribute("currentList");
		
		String countryCodeSalvato= (String)session.getAttribute("countryCodeSalvato");
		
		String targetServletBottone= (String)session.getAttribute("targetServletBottone");

		if(request.getParameter("idcitta")!=null) {
			currentCityId= Integer.parseInt(request.getParameter("idcitta"));

		}



		if(request.getParameter("newcitta")!=null) {
			newcitta=true;
			currentCityId=0;
		}
		else {
			newcitta=false;
		}


		if (request.getParameter("creacitta")!=null) {

			nomeDaSalvare = (String)request.getParameter("nomecittainserito");
			distrettoDaSalvare = (String)request.getParameter("nomedistrettoinserito");
			popolazioneDaSalvare = Integer.parseInt(request.getParameter("popolazioneinserita"));

			if(currentCityId!=0) {
				daoCity.updateCitta(currentCityId, nomeDaSalvare, distrettoDaSalvare, popolazioneDaSalvare);
			}
			else {
				nazioneSelezionata = request.getParameter("nazioneselezionata");
				String countryCode= daoCity.trovaCountryCodeDaNazione(nazioneSelezionata);
				daoCity.insertCitta(nomeDaSalvare, countryCode, distrettoDaSalvare, popolazioneDaSalvare);
			}
		}	
		if(currentList=="Citta") {
			lista= daoCity.getAllCitiesByNation(countryCodeSalvato);
		}
		else {
			lista=null;

		}
		request.setAttribute("listaCitta", lista);
		cittaDaModificare = daoCity.cittaDaModificare(currentCityId);
		List<String> allnationnames= daoNation.allNationNames(currentNation);
		session.setAttribute("allnations", allnationnames);
		session.setAttribute("cittaDaModificare", cittaDaModificare);
		session.setAttribute("currentCityId", currentCityId);
		request.setAttribute("newcitta", newcitta);
		session.setAttribute("targetServletBottone", targetServletBottone);
		request.getRequestDispatcher("inizio.jsp").forward(request, response);

	}



}
