package it.objectmethod.jdbctutorial3.dao;

import java.util.List;

import it.objectmethod.jdbctutorial3.model.City;

public interface IDaoCity {
    public List<City> getAllCitiesByNation(String nation);
    public List<City> ritornaCitta(String cercacitta);
    public City cittaDaModificare(int id);
    public void updateCitta(int idCittaDaModificare, String nome, String distretto, int popolazione);
    public void insertCitta(String nome, String countryCode, String distretto, int popolazione);  
    public String trovaCountryCodeDaNazione(String nazione);
    
    
}
