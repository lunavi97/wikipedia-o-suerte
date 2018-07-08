package search;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WikipediaOSuerte {

    public WikipediaOSuerte() {
        System.setProperty("http.agent", "Brave"); 
    }

    /**
     * Buscar información de un tema específico
     * @param tema - Búsqueda solicitada
     * @return Resultado de la búsqueda
     * @throws IOException
     */
    public String solve(String tema) throws IOException {
        String respuesta;

        try {
            respuesta = buscarEnWikipedia(tema);
        } catch(FileNotFoundException e) { // Si no se encuentra en Wikipedia
            respuesta = voyATenerSuerte(tema);
        }

        return respuesta;
    }

    /**
     * Buscar en Wikipedia
     * @param tema - Búsqueda solicitada
     * @return La URL de la página y el primer párrafo
     * @throws IOException
     */
    private String buscarEnWikipedia(String tema) throws IOException {
        String urlString = "https://es.wikipedia.org/wiki/" + tema;
        String content = getContentWithURL(urlString);
        urlString = firstLine(content);
        content = firstParagraphOfWikipediaPage(content);
        content = sanitization(content);
        return urlString + "\n" + content;
    }

    /**
     * Buscar con voy a tener suerte
     * @param tema - Búsqueda solicitada
     * @return La URL de la página obtenida
     * @throws IOException
     */
    private String voyATenerSuerte(String tema) throws IOException {
        String urlString = "https://google.com.ar/search?btnI&q=" + tema;
        return connectedURL(urlString);
    }


    /**
     * Obtener contenido con URL
     * @param urlString - Texto con la URL
     * @return Una línea con la URL de conexión y el contenido de la página
     * @throws IOException
     */
    private String getContentWithURL(String urlString) throws IOException {
        URL url = new URL(urlString);
        URLConnection urlConnection = url.openConnection();
        InputStream is = urlConnection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        StringBuffer content = new StringBuffer();
        String linea;
        while((linea = br.readLine()) != null) {
            content.append(linea);
        }

        br.close();
        is.close();

        return urlConnection.getURL() + "\n" + content.toString();
    }

    /**
     * Obtener la primera línea de un texto con varias lineas
     * @param text - Texto
     * @return Primera línea
     */
    private String firstLine(final String text) {
        return text.substring(0, text.indexOf("\n"));
    }

    /**
     * Obtener el primer párrafo de una página de Wikipedia
     * @param content - Contenido en HTML de la página de Wikipedia
     * @return Primer párrafo de la página de Wikipedia
     */
    private String firstParagraphOfWikipediaPage(final String content) {
        String principio = content;
        int posInicio = principio.indexOf("<p><b>") + 6;
        int posFin = principio.indexOf("</p>");
        principio = principio.substring(posInicio, posFin);
        return principio;
    }

    /**
     * Limpiar texto de etiquetas HTML y otros caracteres no deseados
     * @param text - Texto HTML a limpiar
     * @return Texto limpio
     */
    private String sanitization(final String text) {

        String result = text;
        final String HTMLTAG = "<.*?>|\\[.*?\\]|&#.\\w+;?";

        final Pattern pattern = Pattern.compile(HTMLTAG, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(result);

        while (matcher.find()) {
            result = result.replace(matcher.group(0), "");
            for (int i = 1; i <= matcher.groupCount(); i++) {
                result = result.replace(matcher.group(i), "");
            }
        }

        matcher = pattern.matcher(result);

        while (matcher.find()) {
            result = result.replace(matcher.group(0), "");
            for (int i = 1; i <= matcher.groupCount(); i++) {
                result = result.replace(matcher.group(i), "");
            }
        }

        return result.trim();
    }

    /**
     * Obtener la URL tras realizar la conexión
     * @param urlString - URL original
     * @return Texto con la URL de conexión
     * @throws IOException
     */
    private String connectedURL(final String urlString) throws IOException {
        URL url = new URL(urlString);
        URLConnection urlConnection = url.openConnection();
        urlConnection.getContentEncoding();
        return urlConnection.getURL().toString();
    }

}
