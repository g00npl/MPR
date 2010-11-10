package pl.com.silnia.test;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import mpr.wejsciowka.SkomplikowaneOperacjeMatematyczne;

public class SilniaTest {
	public SkomplikowaneOperacjeMatematyczne costam;
	
	
	@Before
	public void setUp(){
		costam = new SkomplikowaneOperacjeMatematyczne();
	}
	@Test
	public void Good1Test(){
	int cos = costam.silnia(2);
	assertEquals(cos, 2);
	}
	
	@Test
	public void Good2Test(){
		int proba = 3;
		int cos = costam.silnia(proba);
		assertEquals(cos, 6);	

	
	}
	
	@Test
	public void OneTest(){
		int proba = 1;
		int cos = costam.silnia(proba);
		assertEquals(cos, 1);
	}
	
	@Test
	public void ZeroTest(){
		int proba = 0;
		int cos = costam.silnia(proba);
		assertEquals(cos, 1);
	}
	
	@Test
	public void MinusTest(){
		int proba = -2;
		int cos = costam.silnia(proba);
	}
	
	@Test
	public void TooBigTest(){
		for (int iterator = 0; iterator < 20;  iterator++){
			int cos = costam.silnia(iterator);
			if(cos < 0){
				System.out.println(iterator);
				break;
			}
			
		}
}
}
