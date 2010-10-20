package pl.com.zoo.data;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import pl.com.zoo.basic.Animal;
import pl.com.zoo.basic.Class;
import static org.junit.Assert.*;

public class FileManagerTest {
	FileManager fm;
	Map<Class, Set<Animal>> animals;
	Class gromada;
	Animal animal;
	String className = "anyClass";
	

	@Before
	public void setUp(){
		fm = new FileManager("data/test.bin");
		assertNotNull(fm);
		createTestMap();
		
	}
	
	private void createTestMap(){
		animals = new HashMap<Class, Set<Animal>>();
		gromada = new Class(className);
		animal = new Animal("anyName", "anySpecies", 10);
		
		Set<Animal> set = new HashSet<Animal>();
		animals.put(gromada, set);
		
	}
	@Test
	public void saveAndReadTest(){
	
	try {
		fm.saveAnimalsRegister(animals);
		Map<Class, Set<Animal>> animalsFromFile = fm.readAnimalsRegister();
		assertNotNull(animalsFromFile);
		assertTrue(animalsFromFile.keySet().size() == 1);
		Class classFromFile = animalsFromFile.keySet().iterator().next();
		assertNotNull(classFromFile);
		assertEquals(gromada, classFromFile);
	}  catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	}
}
