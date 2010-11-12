package pl.com.zoo.main;

import pl.com.zoo.basic.Animal;
import pl.com.zoo.basic.Class;
import pl.com.zoo.data.db.HsqlDataManger;

public class Zoo {

	public static void main(String[] args) {
		System.out.println("Witaj w ZOO!\n\n\n");

		HsqlDataManger manager = new HsqlDataManger();
		manager.getAll();

		
	}

}
