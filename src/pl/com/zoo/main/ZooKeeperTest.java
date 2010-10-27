package pl.com.zoo.main;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.com.zoo.basic.Animal;
import pl.com.zoo.basic.Class;

public class ZooKeeperTest {

	private ZooKeeper keeper;
	private int initialMapSize;
	
	@Before
	public void setUp(){
		keeper = new ZooKeeper("data/test.bin");
		keeper.makeAnimalsRegister();
		keeper.saveAnimalsToFile();
		initialMapSize = keeper.animals.size();
	}
	
	@Test
	public void addAnimalNullClassTest(){
		Animal newAnimal = new Animal("test","test",0);
		try{
			keeper.addAnimal(null, newAnimal);
		} catch (Exception e){
			fail();
		}
		keeper.readAnimalsFromFile();
		
		keeper.printAnimals();
		
		int size = keeper.animals.size();
		assertEquals("Liczba klas sie zmienila",initialMapSize,size);
		
		boolean found = false;
		outer: for(Class c : keeper.animals.keySet())
			for(Animal a : keeper.animals.get(c))
				if(a.equals(newAnimal)){
					found=true;
					break outer;
				}
		assertFalse("Dodano zwierze do spisu mimo ze klasa jest null"
				,found);
	}
	
	@Test
	public void addAnimalToExistingClassTest(){
		Class existing = new Class("mammals");
		Animal newAnimal = new Animal("test","test",0);
		keeper.addAnimal(existing, newAnimal);
		keeper.readAnimalsFromFile();
		
		int size = keeper.animals.size();
		assertEquals("Liczba klas sie zmienila",initialMapSize,size);
		
		boolean found = false;
		outer: for(Class c : keeper.animals.keySet())
			for(Animal a : keeper.animals.get(c))
				if(a.equals(newAnimal) && c.equals(existing)){
					found=true;
					break outer;
				}
		assertTrue("Nie dodano zwierzecia do spisu lub dodano" +
				"do niewlasciwej klasy",found);
	}
	
	@Test
	public void addAnimalToNewClassTest(){
		Class newClass = new Class("test");
		Animal newAnimal = new Animal("test","test",0);
		keeper.addAnimal(newClass, newAnimal);
		keeper.readAnimalsFromFile();
		
		int size = keeper.animals.size();
		assertEquals("Liczba klas powinna zwiekszyc sie o 1"
				,initialMapSize+1,size);
		
		boolean found = false;
		outer: for(Class c : keeper.animals.keySet())
			for(Animal a : keeper.animals.get(c))
				if(a.equals(newAnimal) && c.equals(newClass)){
					found=true;
					break outer;
				}
		assertTrue("Nie dodano zwierzecia do spisu lub dodano" +
				"do niewlasciwej klasy",found);
	}
	
}
