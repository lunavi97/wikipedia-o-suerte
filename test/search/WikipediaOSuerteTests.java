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
	(timeout = 6000)
	public void busquedaEnWikipedia() throws IOException {
	    String res = "https://es.wikipedia.org/wiki/Bitcoin\n"
	            + "Bitcoin (signo: ; abr.: BTC, XBT) es un protocolo y red P2P "
	            + "que se utiliza como criptomoneda, sistema de pago y mercancía. "
	            + "Su unidad de cuenta nativa se denomina indistintamente bitcoin o bitcóin. "
	            + "Esas unidades son las que sirven para contabilizar y transferir valor "
	            + "por lo que se clasifican como moneda digital. "
	            + "Concebida en 2009, se desconoce la identidad última "
	            + "de su creador o creadores, apareciendo con el seudónimo de Satoshi Nakamoto. "
	            + "Se sustenta en la tecnología de «cadena de bloques», "
	            + "difícilmente falsificable y semejante a un gran libro contable, "
	            + "público y distribuido, en el que queda reflejado el histórico "
	            + "de todas las transacciones.";
	    assertEquals(res, wos.solve("bitcoin"));
	    res = "https://es.wikipedia.org/wiki/Ethereum\n"
	            + "Ethereum es una plataforma open source, "
	            + "descentralizada que permite la creación de acuerdos"
	            + " de contratos inteligentes entre pares, "
	            + "basada en el modelo blockchain."
	            + "​ Cualquier desarrollador puede crear y publicar aplicaciones"
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
	(timeout = 5000)
	public void busquedaConSuerte() throws IOException {
		assertEquals("https://everipedia.org/", wos.solve("Everipedia"));
		assertEquals("https://lympo.io/", wos.solve("Lympo"));
	}

}
