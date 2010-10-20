package pl.com.zoo.basic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ClassTest {

	Class gromada;
	String name = "xyz";
	
	@Before
	public void setUp(){
//		System.out.println("setup");
		gromada = new Class(name);
		assertNotNull("Nowa utworzony obiekt typu Class jest nullem", gromada);
		
	}
	
	@Test
	public void setGetNameTest() {
//		System.out.println("setgetname");
		Class gromada = new Class("xyz");
		String newName = "abc";
		gromada.setName(newName);
		assertEquals(newName, gromada.getName());

	}
	@Test
	public void getNameTest() {
//		System.out.println("getname");
		assertEquals(name, gromada.getName());
	}
}
