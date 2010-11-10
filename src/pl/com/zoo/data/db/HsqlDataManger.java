package pl.com.zoo.data.db;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;

import pl.com.zoo.basic.Animal;
import pl.com.zoo.basic.Class;
import pl.com.zoo.data.DataManager;

public class HsqlDataManger implements DataManager {
	private Connection con;
	private String animalTableName = "animal";
	private String classTableName = "class";
	private Statement stmt;
	
	private void makeConnection() {
		try {
			con = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/zoodb", "SA", "");
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Wyjatek przy polaczeniu do bazy.");
		}
		assert (con != null);
	}

	public HsqlDataManger() {
		makeConnection();
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean addOrAnimalUpdate(Class cl, Animal animal) {
		try {
			PreparedStatement ps = con
					.prepareStatement("Insert into animal(name, species, weight) values (?,?,?,?)");
			ps.setString(1, animal.getName());
			ps.setString(2, animal.getSpecies());
			ps.setDouble(3, animal.getWeight());
			ps.setInt(4, addOrUpdateClass(cl));
			//ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void removeAnimal(Animal animal) {
		// TODO Auto-generated method stub

	}

	@Override
	public int addOrUpdateClass(Class cl) {
		try {
			PreparedStatement ps = con.prepareStatement("Insert into " +classTableName + "(name) values (?)");
			ps.setString(1, cl.getName());
			ps.executeUpdate();
			ResultSet rs = stmt.executeQuery("SELECT MAX(ID) from " + classTableName);
			int maxId = 0;
			if(rs.next())
				return rs.getInt(1);		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
		

	}

	@Override
	public void removeClass(Class cl) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<Class, Set<Animal>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
