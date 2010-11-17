package pl.com.zoo.main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pl.com.zoo.basic.Animal;
import pl.com.zoo.basic.Class;
import pl.com.zoo.data.DataManager;
import pl.com.zoo.data.db.HsqlDataManager;

public class ZooKeeper {
	public Map<Class, Set<Animal>> animals = new HashMap<Class, Set<Animal>>();

	private DataManager dm;

	public ZooKeeper() {
		dm = new HsqlDataManager();
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

	public boolean addAnimal(Class cl, Animal an) {
		if (cl == null) {
			System.err.println("Klasa jest nullem, przerywam");
			return false;
		}
		if (an == null) {
			System.err.println("Zwierze jest nullem, przerywam");
			return false;
		}
		for (Class c : animals.keySet())
			for (Animal a : animals.get(c))
				if (a.equals(an)) {
					System.err
							.println("Chcesz Dodac Istniejece Zwierze, przerywam.");
					return false;
				}
		boolean found = false;
		for (Class c : animals.keySet())
			if (c.equals(cl)) {
				animals.get(c).add(an);
				found = true;
			}
		if (!found) {
			Set<Animal> newSet = new HashSet<Animal>();
			newSet.add(an);
			animals.put(cl, newSet);
		}
		return found;
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

		for (Class c : animals.keySet())
			for (Animal a : animals.get(c)) {
				dm.addOrAnimalUpdate(c, a);
			}
	}
}
