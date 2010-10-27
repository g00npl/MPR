package pl.com.zoo.basic;

import java.io.Serializable;

public class Animal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name; //imie
	private String species; //gatunek
	//Date birthYear;
	private double weight;

	public Animal(String name, String species, double weight){
		this.name = name;
		this.species = species;
		assert (weight >= 0) : "waga powinna byc wieksza lub rowna 0";
		this.weight = weight;
	}

	@Override
	public boolean equals(Object obj) {
		if( ! (obj instanceof Animal) )
			return false;
		Animal objj = (Animal)obj;
		if( ! ( this.name.equals(objj.name) &&
				this.species.equals(objj.species) &&
				this.weight==objj.weight) )
			return false;
		else
			return true;
	}
	
	@Override
	public String toString() {
		return "Animal: "+species+" "+name+" "+weight;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}

	public double getWeight(){
		return weight;
	}
	public void setWeight(double weight){
		this.weight = weight;
	}

	public String getSpecies(){
		return species;
	}
	public void setSpecies(String species){
		this.species = species;
	}

	

}