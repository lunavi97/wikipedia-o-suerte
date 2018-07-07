package search;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.html.HTML;

import org.omg.PortableInterceptor.PolicyFactory;

public class WikipediaOSuerte {

	public String solve(String tema) throws IOException {
		String respuesta;
		
		try {
			respuesta = buscarEnWikipedia(tema);
		} catch(FileNotFoundException e) {
			respuesta = voyATenerSuerte(tema);
		}
		
		return respuesta;
	}
	
	private String buscarEnWikipedia(String tema) throws IOException {
		String urlString = "https://es.wikipedia.org/wiki/" + tema;
		String content = getContent(urlString);
		content = principioDeWikipedia(content);
		content = sanitization(content);
		return urlString + "\n" + content;
	}
	
	private String voyATenerSuerte(String tema) throws MalformedURLException {
		return "https://google.com.ar/search?btnI&q=" + tema;
	}
	
	
	private String getContent(String urlString) throws IOException {
	    System.setProperty("http.agent", "Brave");
		URL url = new URL(urlString);
		URLConnection urlConnection = url.openConnection();
		InputStream is = urlConnection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String content = "";
		String linea = br.readLine();
		while(linea != null) {
			content += linea;
			linea = br.readLine();
		}
		return content;
	}
	
	private String principioDeWikipedia(String content) {
	    String principio = content;
	    int posInicio = principio.indexOf("<p><b>") + 6;
	    int posFin = principio.indexOf("</p>");
	    principio = principio.substring(posInicio, posFin);
        return principio;
	}
	
	private String sanitization(String text) {
	    
	    String result = text;
	    final String HTMLTAG = "<.*?>|\\[.*?\\]|&#.\\w+;?";
	    
	    final Pattern pattern = Pattern.compile(HTMLTAG, Pattern.MULTILINE);
	    final Matcher matcher = pattern.matcher(result);
	    
	    while (matcher.find()) {
	        result = result.replace(matcher.group(0), "");
	        for (int i = 1; i <= matcher.groupCount(); i++) {
	            result = result.replace(matcher.group(i), "");
	        }
	    }
	    
	    return result;
	}
	
}
