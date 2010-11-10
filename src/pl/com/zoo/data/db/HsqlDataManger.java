package pl.com.zoo.data.db;

import java.util.Map;
import java.util.Set;

import pl.com.zoo.basic.Animal;
import pl.com.zoo.basic.Class;
import pl.com.zoo.data.DataManager;

public class HsqlDataManger implements DataManager {

	@Override
	public boolean addOrAnimalUpdate(Class cl, Animal animal) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeAnimal(Animal animal) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addClass(Class cl) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeClass(Class cl) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<Class, Set<Animal>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
