package search;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class WikipediaOSuerteTests {

	WikipediaOSuerte wos;
	
	@Before
	public void initialize() {
		wos = new WikipediaOSuerte();
	}
	
	@Test
	public void busquedaEnWikipedia() throws IOException {
		assertEquals("https://es.wikipedia.org/wiki/Bitcoin", wos.solve("Bitcoin"));
		assertEquals("https://es.wikipedia.org/wiki/Ethereum", wos.solve("Ethereum"));
		assertEquals("https://es.wikipedia.org/wiki/Steemit", wos.solve("Steemit"));
	}
	
	@Test
	public void busquedaConSuerte() throws IOException {
		assertEquals("https://google.com.ar/search?btnI&q=Everipedia", wos.solve("Everipedia"));
		assertEquals("https://google.com.ar/search?btnI&q=Lympo", wos.solve("Lympo"));
	}

}
