package pl.com.zoo.main;

import pl.com.zoo.basic.Animal;
import pl.com.zoo.basic.Class;
import pl.com.zoo.data.db.HsqlDataManager;

public class Zoo {

	public static void main(String[] args) {
		System.out.println("Witaj w ZOO!\n\n\n");
		ZooKeeper zk = new ZooKeeper();
		Animal spider = new Animal("Puszek", "spider", 0.3);
		//
		Class mammals = new Class("mammals");
		// zk.addAnimal(mammals, spider);
		// zk.printAnimals();

		HsqlDataManager manager = new HsqlDataManager();
		zk.makeAnimalsRegister();
		zk.printAnimals();
		zk.saveAnimalRegister();
		// manager.getAll();

	}

}
