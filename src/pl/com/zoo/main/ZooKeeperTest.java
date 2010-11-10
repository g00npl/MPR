package pl.com.zoo.main;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.com.zoo.basic.Animal;
import pl.com.zoo.basic.Class;

public class ZooKeeperTest {

	private ZooKeeperOld keeper;
	private int initialMapSize;

	@Before
	public void setUp() {
		keeper = new ZooKeeperOld("data/test.bin");
		keeper.makeAnimalsRegister();
		keeper.saveAnimalsToFile();
		initialMapSize = keeper.animals.size();
	}

	@Test
	public void addAnimalNullClassTest() {
		Animal newAnimal = new Animal("test", "test", 0);
		try {
			keeper.addAnimal(null, newAnimal);
		} catch (Exception e) {
			fail();
		}
		keeper.readAnimalsFromFile();

//		keeper.printAnimals();

		int size = keeper.animals.size();
		assertEquals("Liczba klas sie zmienila", initialMapSize, size);

		boolean found = false;
		outer: for (Class c : keeper.animals.keySet())
			for (Animal a : keeper.animals.get(c))
				if (a.equals(newAnimal)) {
					found = true;
					break outer;
				}
		assertFalse("Dodano zwierze do spisu mimo ze klasa jest null", found);
	}

	@Test
	public void addNullAnimalClassTest() {

		Class newclass = new Class("test");
		try {
			keeper.addAnimal(newclass, null);
		} catch (Exception e) {
			fail();
		}
		keeper.readAnimalsFromFile();

//		keeper.printAnimals();
		int size = keeper.animals.size();
		assertEquals("Liczba klas sie zmienila", initialMapSize, size);
		boolean found = false;
		outer: for (Class c : keeper.animals.keySet())
			for (Animal a : keeper.animals.get(c))
				if (a.equals(null)) {
					found = true;
					break outer;
				}
		assertFalse("Dodano zwierze do spisu mimo ze zwierze jest null", found);
	}

	@Test
	public void addExistingAnimalToClassTest() {
		Class existing = new Class("mammals");
		Animal tygrys = new Animal("Pimpus", "tiger", 200.5);
		keeper.saveAnimalsToFile();
		keeper.readAnimalsFromFile();
		keeper.addAnimal(existing, tygrys);
//		keeper.printAnimals();
		int size = keeper.animals.size();
		assertEquals("Liczba klas sie zmienila", initialMapSize, size);

		int found = 0;
		for (Class c : keeper.animals.keySet())
			for (Animal a : keeper.animals.get(c))
				if (a.equals(tygrys)) {
					found = +1;
				}
		assertTrue(found < 2);

	}

	@Test
	public void addAnimalToExistingClassTest() {
		Class existing = new Class("mammals");
		Animal newAnimal = new Animal("test", "test", 0);
		keeper.addAnimal(existing, newAnimal);
		keeper.readAnimalsFromFile();

		int size = keeper.animals.size();
		assertEquals("Liczba klas sie zmienila", initialMapSize, size);

		boolean found = false;
		outer: for (Class c : keeper.animals.keySet())
			for (Animal a : keeper.animals.get(c))
				if (a.equals(newAnimal) && c.equals(existing)) {
					found = true;
					break outer;
				}
		assertTrue("Nie dodano zwierzecia do spisu lub dodano"
				+ "do niewlasciwej klasy", found);
	}

	@Test
	public void addAnimalToNewClassTest() {
		Class newClass = new Class("test");
		Animal newAnimal = new Animal("test", "test", 0);
		keeper.addAnimal(newClass, newAnimal);
		keeper.readAnimalsFromFile();

		int size = keeper.animals.size();
		assertEquals("Liczba klas powinna zwiekszyc sie o 1",
				initialMapSize + 1, size);

		boolean found = false;
		outer: for (Class c : keeper.animals.keySet())
			for (Animal a : keeper.animals.get(c))
				if (a.equals(newAnimal) && c.equals(newClass)) {
					found = true;
					break outer;
				}
		assertTrue("Nie dodano zwierzecia do spisu lub dodano"
				+ "do niewlasciwej klasy", found);
	}

	@Test
	public void removeExistingAnimalTest() {
		Animal tygrys = new Animal("Pimpus", "tiger", 200.5);
		keeper.removeAnimal(tygrys);
//		System.out.println("=====Po usunieciu=====");
//		keeper.printAnimals();

		int size = keeper.animals.size();
		assertEquals("Liczba klas powinna zwiekszyc sie o 1", initialMapSize,
				size);

		boolean found = false;
		outer: for (Class c : keeper.animals.keySet())
			for (Animal a : keeper.animals.get(c))
				if (a.equals(tygrys)) {
					found = true;
					break outer;
				}
		assertFalse("Nie usunieto zwierzecia", found);

	}

	@Test
	public void removeNewAnimalTest() {
		Animal newAnimal = new Animal("nowe", "nowe", 200.5);
//		System.out.println("=====Po usunieciu=====");
//		keeper.printAnimals();

		int size = keeper.animals.size();
		assertEquals(initialMapSize, size);

		assertFalse(keeper.removeAnimal(newAnimal));
	}

	@Test
	public void removeNullTest() {
//		System.out.println("=====Po usunieciu=====");
//		keeper.printAnimals();
		int size = keeper.animals.size();
		assertEquals(initialMapSize, size);
		assertFalse(keeper.removeAnimal(null));
	}
}
