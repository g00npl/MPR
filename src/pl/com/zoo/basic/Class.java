package pl.com.zoo.basic;

import java.io.Serializable;

//gromada
public class Class implements Serializable{

	private String name;

	public Class (String name){
		this.name = name;
	}

	@Override
	public String toString() {
		return "Class: "+name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if( ! (obj instanceof Class) )
			return false;
		Class objj = (Class)obj;
		if( ! (this.name.equals(objj.name)) )
			return false;
		else
			return true;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

}
