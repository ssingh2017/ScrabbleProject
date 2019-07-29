package tests.com.pelatro.scrabble;

import org.junit.*;

import com.pelatro.scrabble.AlphabetRepo;
import com.pelatro.scrabble.Scrabble;
import com.pelatro.scrabble.TakeInput;
import com.pelatro.scrabble.WordAttributes;
import com.pelatro.scrabble.WordBuilders;

public class ScrabbleTests {
	private Scrabble s;
	private TakeInput t;
	private WordBuilders wb;
	private AlphabetRepo ar;
	private WordAttributes wa;
	@Before
	public void setUp() {
		s = new Scrabble();
		t=new TakeInput();
		ar=new AlphabetRepo();
		wa = new WordAttributes();
	}
	 
	@Test
	public void configFileNotPresent() {
		Assert.assertNotNull(s.loadConfigurationFile());  
	}
	
	@Test
	public void testInputNotNUll() {
		String word=t.getInput();
		Assert.assertNotNull(word);
	}
	
	@Test
	public void testProperInput() {
		String word = t.getInput();
		Assert.assertTrue(word.matches("[a-z]+"));
	}
	
//	@Test
//	public void testWordId() {
//		String n=""+wa.getWordId();
//		Assert.assertTrue(n.matches("[0-9]+"));
//	}
//	
	
	
	
	
	
}
