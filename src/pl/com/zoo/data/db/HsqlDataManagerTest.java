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
import pl.com.zoo.basic.Class;
import pl.com.zoo.main.ZooKeeper;

public class HsqlDataManagerTest {
	HsqlDataManager hsqldb = new HsqlDataManager();

	@Before
	public void setUp() {
		hsqldb.setAnimalTableName("animal_test");
		hsqldb.setClassTableName("class_test");
		hsqldb.setHost("localhost");

	}

	@Test
	public void maketablesTest() {
		hsqldb.maketables();
		int licznik = 0;
		try {
			ResultSet meta2 = hsqldb.con.getMetaData().getTables(null, null,
					null, null);
			while (meta2.next()) {
				if (meta2.getString("TABLE_NAME").equals(
						hsqldb.getClassTableName().toUpperCase())) {
					System.err.println("Tabela CLASS istnieje.");
					licznik = 1;
				}

			}// odpetli

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(licznik == 1);

		licznik = 0;
		try {
			ResultSet meta1 = hsqldb.con.getMetaData().getTables(null, null,
					null, null);
			while (meta1.next()) {

				if (meta1.getString("TABLE_NAME").equals(
						hsqldb.getAnimalTableName().toUpperCase())) {
					System.err.println("Tabela ANIMAL istnieje.");
					licznik = 1;
				}

			}// odpetli

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(licznik == 1);
	}

	// Testowanie metody addAnimal
	@Test
	public void addnewAnimalTest() {
		Class testclass = new Class("klasa_testowa");
		Animal testanimal = new Animal("tescik", "gatunek", 20.0);
		boolean check = hsqldb.addOrAnimalUpdate(testclass, testanimal);
		assertTrue(check);
		try {
			ResultSet rs = hsqldb.stmt
					.executeQuery("SELECT name, species, weight from "
							+ hsqldb.getAnimalTableName() + " where name='"
							+ testanimal.getName() + "'");
			while (rs.next()) {
				String aname = rs.getString("name");
				String aspecies = rs.getString("species");
				double aweight = rs.getDouble("weight");
				assertTrue(aname == testanimal.getName());
				assertTrue(aspecies == testanimal.getSpecies());
				assertTrue(aweight == testanimal.getWeight());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addexistAnimalTest() {
		Class testclass = new Class("klasa_testowa");
		Animal testanimal = new Animal("tescik", "gatunek", 20.0);
		hsqldb.addOrAnimalUpdate(testclass, testanimal);
		boolean check = hsqldb.addOrAnimalUpdate(testclass, testanimal);
		assertFalse(check);
		int licznik = 0;
		try {
			ResultSet rs = hsqldb.stmt
					.executeQuery("SELECT name, species, weight from "
							+ hsqldb.getAnimalTableName() + " where name='"
							+ testanimal.getName() + "'");
			while (rs.next()) {
				licznik = +1;
				String aname = rs.getString("name");
				String aspecies = rs.getString("species");
				double aweight = rs.getDouble("weight");
				assertTrue(aname == testanimal.getName());
				assertTrue(aspecies == testanimal.getSpecies());
				assertTrue(aweight == testanimal.getWeight());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(licznik == 1);
	}

	@Test
	public void addNullAnimalTest() {
		Class testclass = new Class("klasa_testowa");
		Animal testanimal = new Animal(null, null, 0);
		boolean check = hsqldb.addOrAnimalUpdate(testclass, testanimal);
		assertFalse(check);
	}

	@Test
	public void addeditAnimalTest() {
		Class testclass = new Class("klasa_testowa");
		Animal testanimal = new Animal("tescik", "gatunek", 20.0);
		hsqldb.addOrAnimalUpdate(testclass, testanimal);
		Animal testanimal1 = new Animal("tescik", "gatunek", 22.0);
		boolean check = hsqldb.addOrAnimalUpdate(testclass, testanimal1);
		assertTrue(check);
		int licznik = 0;
		try {
			ResultSet rs = hsqldb.stmt
					.executeQuery("SELECT name, species, weight from "
							+ hsqldb.getAnimalTableName() + " where name='"
							+ testanimal.getName() + "'");
			while (rs.next()) {
				licznik = +1;
				String aname = rs.getString("name");
				String aspecies = rs.getString("species");
				double aweight = rs.getDouble("weight");
				assertTrue(aname == testanimal1.getName());
				assertTrue(aspecies == testanimal1.getSpecies());
				assertTrue(aweight == testanimal1.getWeight());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(licznik == 1);

	}

	// Testowanie metody addClass
	@Test
	public void addnewClassTest() {
		Class testclass = new Class("klasa_testowa");
		int check = hsqldb.addOrUpdateClass(testclass);
		assertTrue(check > 0);

		int licznik = 0;
		try {
			ResultSet rs = hsqldb.stmt.executeQuery("SELECT id, name from "
					+ hsqldb.getClassTableName() + " where name='"
					+ testclass.getName() + "'");
			while (rs.next()) {
				licznik = +1;
				String cname = rs.getString("name");
				int cid = rs.getInt("id");
				assertTrue(cname == testclass.getName());
				assertTrue(check == cid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(licznik == 1);
	}

	public void addexistClassTest() {
		Class testclass = new Class("klasa_testowa");
		hsqldb.addOrUpdateClass(testclass);
		int check = hsqldb.addOrUpdateClass(testclass);
		assertTrue(check > 0);

		int licznik = 0;
		try {
			ResultSet rs = hsqldb.stmt.executeQuery("SELECT id, name from "
					+ hsqldb.getClassTableName() + " where name='"
					+ testclass.getName() + "'");
			while (rs.next()) {
				licznik = +1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(licznik == 1);
	}

	@Test
	public void addNullClassTest() {
	Class testclass = new Class(null);
	int check = hsqldb.addOrUpdateClass(testclass);
	assertTrue(check == -2);
	}

	// Testowanie metody removeAnimal
	@Test
	public void removeAnimalTest() {
		Class testclass = new Class("klasa_testowa");
		Animal testanimal = new Animal("tescik", "gatunek", 20.0);
		hsqldb.addOrAnimalUpdate(testclass, testanimal);
		hsqldb.removeAnimal(testanimal);
	}

	@Test
	public void removenoexistAnimalTest() {

	}

	@Test
	public void removenullAnimalTest() {

	}

	// Testowanie metody removeClass
	@Test
	public void removeClassTest() {

	}

	@Test
	public void removenoexistClassTest() {

	}

	@Test
	public void removenullClassTest() {

	}

	// Testowanie metody getAll
	@Test
	public void getAllTest() {

	}

	@After
	public void cleantables() {
		try {
			stmt.executeUpdate("drop table " + classTableName);
			stmt.executeUpdate("drop table " + animalTableName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
