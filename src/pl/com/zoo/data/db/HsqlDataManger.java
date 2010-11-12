package pl.com.zoo.data.db;


import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
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
	private String host = "localhost";
	Animal animalgettall;
	Class classgetall;

	private void makeConnection() {
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:hsql://" + host
					+ "/zoodb", "SA", "");
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
			// ps.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void removeAnimal(Animal animal) {
		try {
			PreparedStatement ps = con.prepareStatement("Delete from "
					+ animalTableName + " where name='?'");
			ps.setString(1, animal.getName());
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public int addOrUpdateClass(Class cl) {
		try {
			PreparedStatement ps = con.prepareStatement("Insert into "
					+ classTableName + "(name) values (?)");
			ps.setString(1, cl.getName());
			ps.executeUpdate();
			ResultSet rs = stmt.executeQuery("SELECT MAX(ID) from "
					+ classTableName);
			if (rs.next())
				return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;

	}

	@Override
	public void removeClass(Class cl) {
		try {
			ResultSet rs = stmt.executeQuery("SELECT id from " + classTableName
					+ " where name='" + cl.getName() + "'");

			PreparedStatement ps1 = con.prepareStatement("Delete from "
					+ animalTableName + " where id_class=?");
			ps1.setInt(1, rs.getInt(1));
			ps1.executeUpdate();

			PreparedStatement ps = con.prepareStatement("Delete from "
					+ classTableName + " where name='?'");
			ps.setString(1, cl.getName());
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Map<Class, Set<Animal>> getAll() {
		Map<Class, Set<Animal>> animals = new HashMap<Class, Set<Animal>>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT id, name from "
					+ classTableName);

			while (rs.next()) {
				String name = rs.getString("name");
				int id = rs.getInt("id");

				classgetall = new Class(name);
				Set<Animal> hs = new HashSet<Animal>();

				ResultSet rs1 = stmt
						.executeQuery("SELECT name, species, weight from "
								+ animalTableName + " where id_class=" + id);
				while (rs1.next()) {
					String aname = rs.getString("name");
					String aspecies = rs.getString("species");
					double aweight = rs.getDouble("weight");
					animalgettall = new Animal(aname, aspecies, aweight);
					hs.add(animalgettall);
				}
				animals.put(classgetall, hs);

			}
			for (Class c : animals.keySet()) {
				System.out.println(c);
				for (Animal a : animals.get(c)) {
					System.out.println(a);
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;

	}

}
