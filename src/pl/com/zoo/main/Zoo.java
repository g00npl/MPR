package pl.com.zoo.main;

import pl.com.zoo.basic.Animal;
import pl.com.zoo.basic.Class;

public class Zoo {

	public static void main(String[] args) {
		System.out.println("Witaj w ZOO!\n\n\n");

		ZooKeeper keeper = new ZooKeeper();
		// keeper.readAnimalsFromFile();
		keeper.makeAnimalsRegister();
		keeper.printAnimals();
		keeper.saveAnimalRegister();
		
		
//		
//		Animal orf = new Animal("Orf", "Ogr", 0.3);
//		Class tak = new Class("ogr");
//		Animal goon1 = new Animal("Goonasdasdasd", "skorpion", 0.3);
//		Class tak1 = new Class("arachnids");
//		keeper.addAnimal(tak, orf);
//		keeper.addAnimal(tak1, goon1);
//		keeper.readAnimalsFromFile();
//		keeper.printAnimals();

//		System.out.println("Usuwamy orfa!\n\n\n");
//		keeper.removeAnimal(orf);
//		keeper.readAnimalsFromFile();
//		keeper.printAnimals();
		// System.out.println("\n\nZapis/odczyt z pliku...\n\n");
		// keeper.saveAnimalsToFile();
		// keeper.readAnimalsFromFile();
		// keeper.printAnimals();ssssadasd
	}

}
