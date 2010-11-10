package pl.com.zoo.data;

import java.util.Map;
import java.util.Set;

import pl.com.zoo.basic.Animal;
import pl.com.zoo.basic.Class;

/**
 * Interfejs ktory musza implementowac wszystkie klasy oferujace dostep do
 * danych
 * 
 * @author s7267
 * 
 */
public interface DataManager {

	/**
	 * Zapisuje zwierze nalezace do gromady
	 * 
	 * @param cl
	 * @param animal
	 * @return true jesli gromada istnieje
	 */

	public boolean addOrAnimalUpdate(Class cl, Animal animal);

	/**
	 * Usuwa zwierze
	 * 
	 * @param animal
	 */

	public void removeAnimal(Animal animal);

	/**
	 * Dodaje gromade
	 * 
	 * @param cl
	 */

	public int addOrUpdateClass(Class cl);

	/**
	 * Usuwa gromade i wszystkie zwierzeta
	 * 
	 * @param cl
	 */
	public void removeClass(Class cl);
	
	/**
	 * Zwraca caly spis zwierzat
	 * @return
	 */
	
	public Map<Class, Set<Animal>> getAll();


}
