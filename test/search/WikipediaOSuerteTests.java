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
	    String res = "https://es.wikipedia.org/wiki/Ethereum\n"
	            + "Ethereum es una plataforma open source, "
	            + "descentralizada que permite la creación de acuerdos"
	            + " de contratos inteligentes entre pares, "
	            + "basada en el modelo blockchain."
	            + "[2]​ Cualquier desarrollador puede crear y publicar aplicaciones"
	            + " distribuidas que realicen contratos inteligentes. "
	            + "Ethereum también provee una ficha de criptomoneda "
	            + "que se llama 'ether'."
	            + " Se puede intercambiar ether entre cuentas diferentes y "
	            + "también es utilizado para compensar los nodos participantes"
	            + " por los cálculos realizados.";
		assertEquals(res , wos.solve("Ethereum"));
		res = "https://es.wikipedia.org/wiki/Steemit\n"
		        + "Steemit es una red social que combina la creación de contenido, "
		        + "la interacción con usuarios y el uso de una criptomoneda llamada "
		        + "Steem.";
		assertEquals(res, wos.solve("Steemit"));
	}
	
	@Test
	public void busquedaConSuerte() throws IOException {
		assertEquals("https://google.com.ar/search?btnI&q=Everipedia", wos.solve("Everipedia"));
		assertEquals("https://google.com.ar/search?btnI&q=Lympo", wos.solve("Lympo"));
	}

}
