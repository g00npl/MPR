package pl.com.zoo.main;

public class Zoo {

	public static void main(String[] args) {
		System.out.println("Witaj w ZOO!\n\n\n");

		ZooKeeper keeper = new ZooKeeper();
		keeper.makeAnimalsRegister();
		keeper.printAnimals();
		
		
		System.out.println("\n\nZapis/odczyt z pliku...\n\n");
		keeper.saveAnimalsToFile();
		keeper.readAnimalsFromFile();
		keeper.printAnimals();
	}

}
