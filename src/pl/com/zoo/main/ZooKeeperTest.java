package pl.com.zoo.main;

import static org.junit.Assert.*;




import org.junit.Before;
import org.junit.Test;

import pl.com.zoo.basic.Animal;
import pl.com.zoo.basic.Class;

public class ZooKeeperTest {

	private ZooKeeper keeper;

	@Before
	public void setUp() {
		keeper = new ZooKeeper("data/test.bin");

		keeper.makeAnimalsRegister();
		assertNotNull(keeper);
	}

	@Test
	public void addAnimalToExistingClassTest() {
		int rozmiargromad = keeper.animals.keySet().size();
		Class existing = new Class("mammals");
		Animal newAnimal = new Animal("test", "test", 0);
		
		
		keeper.addAnimal(existing, newAnimal);
		keeper.readAnimalsFromFile();
		assertTrue(keeper.animals.keySet().size() == rozmiargromad);
		
		boolean znalezionezwierze = false;
		outer: for (Class c : keeper.animals.keySet()){
			for (Animal d : keeper.animals.get(c)){
				
				if((d.equals(newAnimal)) &&  c.equals(existing)){
					znalezionezwierze = true;
					break outer;
				}
			}
		}
			
			assertTrue(znalezionezwierze);
		
		
	}
	
	
	@Test
	public void addAnimalNullClass(){
		int size = keeper.animals.size();
		Animal nowezwierze = new Animal("mucha", "mucha", 0);
		try{
		keeper.addAnimal(null, nowezwierze);
		} catch (Exception e){
			fail();
		}
		keeper.readAnimalsFromFile();
		assertTrue(keeper.animals.keySet().size() == size);
		
		boolean znalezionezwierze = false;
		outer: for (Class c : keeper.animals.keySet()){
			for (Animal d : keeper.animals.get(c)){
				
				if((d.equals(nowezwierze))){
					znalezionezwierze = true;
					break outer;
				}
			}
		}
			
			assertFalse(znalezionezwierze);
	}
	
	@Test
	public void addAnimalToNewClass(){
		int rozmiargromad = keeper.animals.keySet().size();
		Class nowaklasa = new Class("test");
		Animal nowezwierze = new Animal("mucha", "mucha", 0);
		
		
		keeper.addAnimal(nowaklasa, nowezwierze);
		keeper.readAnimalsFromFile();
		assertTrue(keeper.animals.keySet().size() == (rozmiargromad+1));
		
		boolean znalezionezwierze = false;
		outer: for (Class c : keeper.animals.keySet()){
			for (Animal d : keeper.animals.get(c)){
				
				if((d.equals(nowezwierze)) &&  c.equals(nowaklasa)){
					znalezionezwierze = true;
					break outer;
				}
			}
		}
			
			assertTrue(znalezionezwierze);
	}
}
