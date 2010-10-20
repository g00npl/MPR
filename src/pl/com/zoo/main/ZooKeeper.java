package pl.com.zoo.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pl.com.zoo.basic.Animal;
import pl.com.zoo.basic.Class;
import pl.com.zoo.data.FileManager;

public class ZooKeeper {

	private Map<Class, Set<Animal>> animals = new HashMap<Class, Set<Animal>>();

	private FileManager fm = new FileManager("data/animals.bin");

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

	public void addAnimal(Class klasa, Animal nazwa) {
		Set<Animal> newhash = new HashSet<Animal>();
		newhash.add(nazwa);
		animals.put(klasa, newhash);

		saveAnimalsToFile();
		
	}

	public void saveAnimalsToFile() {

		try {
			fm.saveAnimalsRegister(animals);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readAnimalsFromFile() {

		try {
			animals = fm.readAnimalsRegister();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void printAnimals() {

		for (Class c : animals.keySet()) {
			System.out.println(c);
			for (Animal a : animals.get(c)) {
				System.out.println(a);
			}
		}

	}
}
