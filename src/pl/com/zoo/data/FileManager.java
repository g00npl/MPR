package pl.com.zoo.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Set;

import pl.com.zoo.basic.Animal;
import pl.com.zoo.basic.Class;

public class FileManager {
	
	private String fileName = "data/animals.bin" ;

	public FileManager(String fileName) {
		this.fileName = fileName;
	}

	public void saveAnimalsRegister(Map<Class, Set<Animal>> animals) throws FileNotFoundException, IOException {
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
		
		oos.writeObject(animals);
		
		
	}

	@SuppressWarnings("unchecked")
	public Map<Class, Set<Animal>> readAnimalsRegister() throws  IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
		
		return (Map<Class, Set<Animal>>)ois.readObject();
	}

	
}
