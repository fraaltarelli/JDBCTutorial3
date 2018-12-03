package it.objectmethod.jdbctutorial3.dao;

import java.util.List;

import it.objectmethod.jdbctutorial3.model.Nation;

public interface IDaoNation {
	
	public List<Nation> getAllNationsByContinent(String continent);
    public List<String> getAllContinents();
    public List<String> allNationNames(String nazione);
    
}
