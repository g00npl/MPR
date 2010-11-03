package pl.com.zoo.data.db;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.com.zoo.basic.Animal;

public class DBTest {
	
	Connection con;
	@Before
	public void setUp(){
		 try {
				 con = DriverManager.getConnection(
				          "jdbc:hsqldb:hsql://localhost/zoodb", "SA", "");
				assertNotNull(con);
			} catch (SQLException e) {
				e.printStackTrace();
				fail("Wyjatek przy polaczeniu do bazy.");
			}
			 
		}
	
@Test
public void insertionTest(){
	try {
		Statement stmt = con.createStatement();
		String aname = "orf";
		String aspecies = "ogr";
		double aweight = 20;
		int up = stmt.executeUpdate("INSERT into animal(name, species, weight) values (''"+aname+"' , '"+aspecies+"', "+aweight+")");
		ResultSet rs = stmt.executeQuery("SELECT MAX(ID) from animal");
		int maxId = 0;
		if(rs.next())
			maxId = rs.getInt(1);		
		else
			fail("Can't get ");
		assertEquals(1, up);
		rs = stmt.executeQuery("SELECT * FROM animal where id=" + maxId);
		if(!rs.next()) fail("Nic nie ma pod id="+maxId);
		assertEquals(aname,rs.getString("name"));
		assertEquals(aspecies, rs.getString("species"));
		assertEquals(aweight, rs.getDouble("weight"), 0.00001);
		//////czyscimy baze
		stmt.executeUpdate("DELETE FROM ANIMAL where id="+maxId);

	} catch (SQLException e) {
		e.printStackTrace();
		fail();
	}
	
	
}
	
@Test
public void selectTest(){
	try {
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM animal");
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String species = rs.getString("species");
			double weight = rs.getDouble("weight");
//			System.out.println("Id: " + id + " Name: " + name + " Species: " + species + " Weight: " + weight);

			Animal an = new Animal(name, species, weight);
			System.out.println(an + " (id: " +id + " )");
	}
	} catch (SQLException e) {
		e.printStackTrace();
		fail("Wyjatek przy zapytaniu.");
	}



	
}




@After
public void closeConnection(){
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
		fail("Nie zamknieto polaczenia");
	}
}
}