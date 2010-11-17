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
import pl.com.zoo.main.ZooKeeper;


public class HsqlDataManager implements DataManager {
	private Connection con;
	private String animalTableName = "animal";
	private String classTableName = "class";
	private Statement stmt;
	private String host = "localhost";
	Animal animalgettall;
	Class classgetall;
	ZooKeeper zk;

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

	public HsqlDataManager() {
		makeConnection();
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void maketables() {
		int licznik = 0;
		try {
			ResultSet meta = con.getMetaData()
					.getTables(null, null, null, null);
			while (meta.next()) {
				if (meta.getString("TABLE_NAME").equals(
						classTableName.toUpperCase())) {
					System.err.println("Tabela CLASS istnieje.");
					licznik = 1;
				}

			}// odpetli

			if (licznik == 0) {
				stmt.executeUpdate("CREATE TABLE class(id int GENERATED BY DEFAULT AS IDENTITY (start with 1) PRIMARY KEY, name varchar(30))");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		licznik = 0;
		try {
			ResultSet meta1 = con.getMetaData().getTables(null, null, null,
					null);
			while (meta1.next()) {

				if (meta1.getString("TABLE_NAME").equals(
						animalTableName.toUpperCase())) {
					System.err.println("Tabela ANIMAL istnieje.");
					licznik = 1;
				}

			}// odpetli

			if (licznik == 0) {
				stmt.executeUpdate("CREATE TABLE animal(id int GENERATED BY DEFAULT AS IDENTITY (start with 1) PRIMARY KEY, name varchar(120), species varchar(60), weight double, id_class int, FOREIGN KEY(id_class) REFERENCES class(id))");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean addOrAnimalUpdate(Class cl, Animal animal) {
		try {
			PreparedStatement ps = con
					.prepareStatement("Insert into animal(name, species, weight, id_class) values (?,?,?,?)");

			ResultSet checkall = stmt
					.executeQuery("Select id from animal where name='"
							+ animal.getName() + "' and species='"
							+ animal.getSpecies() + "' and weight="
							+ animal.getWeight() + "");

			ResultSet checkname = stmt
					.executeQuery("Select id from animal where name='"
							+ animal.getName() + "'");
			
			if(checkall.next()){
				if (checkall.getInt("id") > 0) {
					System.err.println("Animal already exist!");
					return false;
				} 
			}
			
			if(checkname.next()){
				if (checkname.getInt("id") > 0) {
					removeAnimal(animal);
					addOrAnimalUpdate(cl, animal);
					return true;
				}
			}

			
			
			ps.setString(1, animal.getName());
			ps.setString(2, animal.getSpecies());
			ps.setDouble(3, animal.getWeight());
			ps.setInt(4, addOrUpdateClass(cl));
			ps.executeUpdate();
		} catch (SQLException e) {

		e.printStackTrace();
		}
		return false;
	}

	@Override
	public int addOrUpdateClass(Class cl) {
		try {
			PreparedStatement ps = con.prepareStatement("Insert into "
					+ classTableName + "(name) values (?)");

			ResultSet checkname = stmt
					.executeQuery("Select id from class where name='"
							+ cl.getName() + "'");
			if(checkname.next()){
				if (checkname.getInt("id") > 0) {
					System.err.println("Class already exist");
					return checkname.getInt("id");
				}
				
			}
			

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
	public void removeAnimal(Animal animal) {
		try {
			PreparedStatement ps = con.prepareStatement("Delete from "
					+ animalTableName + " where name=?");
			ps.setString(1, animal.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			fail("You want remove animal which not exist");
			// e.printStackTrace();
		}

	}

	@Override
	public void removeClass(Class cl) {
		try {
			ResultSet rs = stmt.executeQuery("SELECT id from " + classTableName
					+ " where name='" + cl.getName() + "'");

			rs.next();
			PreparedStatement ps1 = con.prepareStatement("Delete from "
					+ animalTableName + " where id_class=?");
			ps1.setInt(1, rs.getInt("id"));
			ps1.executeUpdate();

		} catch (SQLException e) {
			fail("Zadne zwierze nie jest przypisane do klasy ktore chcesz usunac.");
			// e.printStackTrace();
		}

		try {
			PreparedStatement ps = con.prepareStatement("Delete from "
					+ classTableName + " where name=?");
			ps.setString(1, cl.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			fail("You want remove class which not exist");
			// e.printStackTrace();
		}

	}

	@Override
	public Map<Class, Set<Animal>> getAll() {
		try {
			zk = new ZooKeeper();
			ResultSet rs = stmt.executeQuery("SELECT id, name from "
					+ classTableName);

			while (rs.next()) {
				String name = rs.getString("name");
				int id = rs.getInt("id");
				classgetall = new Class(name);

				ResultSet rs1 = stmt
						.executeQuery("SELECT name, species, weight from "
								+ animalTableName + " where id_class=" + id);
				while (rs1.next()) {
					String aname = rs1.getString("name");
					String aspecies = rs1.getString("species");
					double aweight = rs1.getDouble("weight");
					animalgettall = new Animal(aname, aspecies, aweight);
					zk.addAnimal(classgetall, animalgettall);
				}

			}
			zk.printAnimals();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;

	}

}
