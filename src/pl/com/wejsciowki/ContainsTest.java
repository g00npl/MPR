package pl.com.wejsciowki;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.text.analyzer.*;

public class ContainsTest {
private LettersFinder lf; 
private LettersFinder lf2;

@Before
public void setup(){
	lf = new LettersFinder();
	lf2 = new LettersFinder("abc");
	}

@Test
public void correctTest(){
boolean corr = lf2.contains("ale bardzoc");	
assertTrue(corr);
}

@Test
public void correct2Test(){
boolean corr = lf2.contains("cacy boy");	
assertTrue(corr);
}


@Test
public void correct3Test(){
boolean corr = lf2.contains("ala ma bobasac");	
assertTrue(corr);
}

@Test
public void nonargumentconstructorTest(){
boolean corr = lf.contains("cacy boy");	
assertTrue(corr);
}

@Test
public void nullTest(){
boolean corr = lf2.contains(null);	
assertFalse(corr);
}

@Test
public void FalseTest(){
boolean corr = lf2.contains("CAcy boy");	
assertFalse(corr);
}
}
