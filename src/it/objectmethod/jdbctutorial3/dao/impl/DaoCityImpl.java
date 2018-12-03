package it.objectmethod.jdbctutorial3.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import it.objectmethod.jdbctutorial3.config.ConnectionManager;
import it.objectmethod.jdbctutorial3.dao.IDaoCity;
import it.objectmethod.jdbctutorial3.model.City;

public class DaoCityImpl implements IDaoCity{

	@Override
	public List<City> getAllCitiesByNation(String countrycode) {

		List<City> list = null;
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement prepStat = null;
		try{

			String sql = "SELECT id,name, district, population from city where countrycode= ?";
			prepStat= conn.prepareStatement(sql);
			prepStat.setString(1, countrycode);
			ResultSet rs = prepStat.executeQuery();
			list = new ArrayList<>();
			//STEP 5: Extract data from result set
			while(rs.next()){
				City city= new City();
				//Retrieve by column name
				int id= rs.getInt("id");
				String name = rs.getString("name");
				String district = rs.getString("district");
				int population = rs.getInt("population");
				city.setId(id);
				city.setNomeCitta(name);
				city.setDistretto(district);
				city.setPopolazione(population);
				list.add(city);
			}

			//STEP 6: Clean-up environment
			rs.close();
			prepStat.close();
			conn.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(prepStat!=null)
					prepStat.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
		System.out.println("Goodbye!");
		return list;
	}

	@Override
	public List<City> ritornaCitta(String cercacitta) {
		
		List<City> list = null;
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement prepStat = null;
		try{

			String sql = "SELECT name, district, population from city where name like ?";
			prepStat= conn.prepareStatement(sql);
			prepStat.setString(1, cercacitta+ "%");
			ResultSet rs =prepStat.executeQuery();
			list = new ArrayList<>();
			//STEP 5: Extract data from result set
			while(rs.next()){
				City city= new City();
				//Retrieve by column name
				String name = rs.getString("name");
				String district = rs.getString("district");
				int population = rs.getInt("population");
				city.setNomeCitta(name);
				city.setDistretto(district);
				city.setPopolazione(population);
				list.add(city);
			}

			//STEP 6: Clean-up environment
			rs.close();
			prepStat.close();
			conn.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(prepStat!=null)
					prepStat.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
		System.out.println("Goodbye!");
		return list;
	}

	@Override
	public City cittaDaModificare(int id) {
		City city= new City();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement prepStat = null;
		try{

			String sql = "SELECT name, district, population from city where id = ?";
			prepStat= conn.prepareStatement(sql);
			prepStat.setInt(1, id);
			ResultSet rs =prepStat.executeQuery();
			
			//STEP 5: Extract data from result set
			while(rs.next()){

				//Retrieve by column name
				String name = rs.getString("Name");
				String district = rs.getString("district");
				int population = rs.getInt("population");
				city.setNomeCitta(name);
				city.setDistretto(district);
				city.setPopolazione(population);
				city.setId(id);
			}

			//STEP 6: Clean-up environment
			rs.close();
			prepStat.close();
			conn.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(prepStat!=null)
					prepStat.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
		System.out.println("Goodbye!");
		return city;
	}

	@Override
	public void updateCitta(int idCittaDaModificare, String nome, String distretto, int popolazione) {
		//City city= new City();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement prepStat = null;

			try{

				String sql = "UPDATE City " + 
						" SET name=?, district=?, population= ?" + 
						" WHERE id=?";
				prepStat= conn.prepareStatement(sql);
				prepStat.setString(1, nome);
				prepStat.setString(2, distretto);
				prepStat.setInt(3, popolazione);
				prepStat.setInt(4, idCittaDaModificare);
				prepStat.executeUpdate();	
				
				//STEP 6: Clean-up environment
				prepStat.close();
				conn.close();
			}catch(SQLException se){
				//Handle errors for JDBC
				se.printStackTrace();
			}catch(Exception e){
				//Handle errors for Class.forName
				e.printStackTrace();
			}finally{
				//finally block used to close resources
				try{
					if(prepStat!=null)
						prepStat.close();
				}catch(SQLException se2){
				}// nothing we can do
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
					se.printStackTrace();
				}//end finally try
			}//end try
			System.out.println("Goodbye!");
			return;
		}
	

	@Override
	public void insertCitta(String nome, String countryCode, String distretto, int popolazione) {
		// TODO Auto-generated method stub
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement prepStat = null;

			try{

				String sql = "INSERT INTO City (Name, CountryCode, District, Population)" + 
						" VALUES (?, ?, ?, ?)";
				prepStat= conn.prepareStatement(sql);
				prepStat.setString(1, nome);
				prepStat.setString(2, countryCode);
				prepStat.setString(3, distretto);
				prepStat.setInt(4, popolazione);
				prepStat.executeUpdate();	
				
				//STEP 6: Clean-up environment
				prepStat.close();
				conn.close();
			}catch(SQLException se){
				//Handle errors for JDBC
				se.printStackTrace();
			}catch(Exception e){
				//Handle errors for Class.forName
				e.printStackTrace();
			}finally{
				//finally block used to close resources
				try{
					if(prepStat!=null)
						prepStat.close();
				}catch(SQLException se2){
				}// nothing we can do
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
					se.printStackTrace();
				}//end finally try
			}//end try
			System.out.println("Goodbye!");
			return;
		}

	@Override
	public String trovaCountryCodeDaNazione(String nazione) {
		// TODO Auto-generated method stub
		String countryCode="";
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement prepStat = null;

			try{

				String sql = "select distinct city.countrycode from city  inner join country on city.countrycode= country.code where country.name = ?";
				prepStat= conn.prepareStatement(sql);
				prepStat.setString(1, nazione);
				ResultSet rs =prepStat.executeQuery();
				//STEP 5: Extract data from result set
				while(rs.next()){
					//Retrieve by column name
					countryCode = rs.getString("countrycode");
				}
				//STEP 6: Clean-up environment
				prepStat.close();
				conn.close();
			}catch(SQLException se){
				//Handle errors for JDBC
				se.printStackTrace();
			}catch(Exception e){
				//Handle errors for Class.forName
				e.printStackTrace();
			}finally{
				//finally block used to close resources
				try{
					if(prepStat!=null)
						prepStat.close();
				}catch(SQLException se2){
				}// nothing we can do
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
					se.printStackTrace();
				}//end finally try
			}//end try
			System.out.println("Goodbye!");
			return countryCode;
	}
	




}
