package pl.com.zoo.main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pl.com.zoo.basic.Animal;
import pl.com.zoo.basic.Class;
import pl.com.zoo.data.DataManager;
import pl.com.zoo.data.db.HsqlDataManger;

public class ZooKeeper {
	protected Map<Class, Set<Animal>> animals = new HashMap<Class, Set<Animal>>();
	
	private DataManager dm;
	
	public ZooKeeper(){
		dm = new HsqlDataManger();
	}

	public void printAnimals() {
		for (Class c : animals.keySet()) {
			System.out.println(c);
			// c.printInfo();
			for (Animal a : animals.get(c)) {
				System.out.println(a);
				// a.printInfo();
			}
		}
	}

	public void makeAnimalsRegister() {
		Animal tiger = new Animal("Pimpus", "tiger", 200.5);
		Animal camel = new Animal("George", "camel", 253);
		Animal spider = new Animal("Puszek", "spider", 0.3);

		Class mammals = new Class("mammals");
		Class arachnids = new Class("arachnids");

		Set<Animal> mam = new HashSet<Animal>();
		mam.add(tiger);
		mam.add(camel);
		Set<Animal> ara = new HashSet<Animal>();
		ara.add(spider);

		animals.put(mammals, mam);
		animals.put(arachnids, ara);
	}

	public void saveAnimalRegister() {

			for(Class c: animals.keySet())
				for(Animal a: animals.get(c)){
					dm.addOrAnimalUpdate(c, a);
				}
	}
}
