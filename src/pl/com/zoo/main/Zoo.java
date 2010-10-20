package pl.com.zoo.main;

import pl.com.zoo.basic.Animal;
import pl.com.zoo.basic.Class;

public class Zoo {

	public static void main(String[] args) {
		System.out.println("Witaj w ZOO!\n\n\n");

		ZooKeeper keeper = new ZooKeeper();
		// keeper.readAnimalsFromFile();
		keeper.makeAnimalsRegister();
		// keeper.printAnimals();

		Animal goon = new Animal("Goon", "skorpion", 0.3);
		Class tak = new Class("arachnids");

		Animal goon1 = new Animal("Goonasdasdasd", "skorpion", 0.3);
		Class tak1 = new Class("mammals");

		keeper.addAnimal(tak, goon);
		keeper.addAnimal(tak1, goon1);
		keeper.readAnimalsFromFile();
		keeper.printAnimals();

		// System.out.println("\n\nZapis/odczyt z pliku...\n\n");
		// keeper.saveAnimalsToFile();
		// keeper.readAnimalsFromFile();
		// keeper.printAnimals();
	}

}
