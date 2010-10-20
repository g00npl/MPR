package pl.com.zoo.main;

public class Zoo {

	public static void main(String[] args) {
		System.out.println("Witaj w ZOO!");

		ZooKeeper keeper = new ZooKeeper();
		keeper.makeAnimalsRegister();
		keeper.printAnimals();
		
		keeper.saveAnimalsToFile();
		keeper.readAnimalsFromFile();

	}

}
