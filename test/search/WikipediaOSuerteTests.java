package search;

import java.io.IOException;

import org.junit.Assert;
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
		Assert.assertEquals("https://es.wikipedia.org/wiki/Bitcoin", wos.solve("Bitcoin"));
		Assert.assertEquals("https://es.wikipedia.org/wiki/Ethereum", wos.solve("Ethereum"));
		Assert.assertEquals("https://es.wikipedia.org/wiki/Steemit", wos.solve("Steemit"));
	}
	
	@Test
	public void busquedaConSuerte() throws IOException {
		Assert.assertEquals("https://google.com.ar/search?btnI&q=Everipedia", wos.solve("Everipedia"));
		Assert.assertEquals("https://google.com.ar/search?btnI&q=Lympo", wos.solve("Lympo"));
	}

}
