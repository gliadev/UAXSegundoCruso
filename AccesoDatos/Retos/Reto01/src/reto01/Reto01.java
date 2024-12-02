/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package reto01;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author gliatem
 */
public class Reto01 {

    /**
     * @param args the command line arguments
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     */
    public static void main(String[] args)  throws ParserConfigurationException, SAXException, IOException {
        // Crear objeto DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Cargar y parsear el archivo XML
        // DAMIAN IGUAL CAMBIA LA RUTA!!! USADO LA ABSOLUTA PERO 
        // EN TU ORDENADOR NO VA IR, que tienes un mac
        Document document = builder.parse(new File("src/reto01/mercados.xml"));
        document.getDocumentElement().normalize();

        // Extraer el nodo raíz
        Element root = document.getDocumentElement();
        System.out.println("Nodo raíz: " + root.getNodeName());

        // Extraer información del canal
        Element channel = (Element) document.getElementsByTagName("channel").item(0);
        String tituloCanal = channel.getElementsByTagName("title").item(0).getTextContent();
        String copyright = channel.getElementsByTagName("copyright").item(0).getTextContent();

        System.out.println("Título del canal: " + tituloCanal);
        System.out.println("Copyright: " + copyright);

        // Extraer las noticias
        NodeList items = channel.getElementsByTagName("item");
        System.out.println("Número de noticias: " + items.getLength());

        for (int i = 0; i < items.getLength(); i++) {
            Node item = items.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) item;

                // Título de la noticia
                String tituloNoticia = elemento.getElementsByTagName("title").item(0).getTextContent();
                String atributoMedia = elemento.getElementsByTagName("media:title").item(0).getTextContent();

                // Creador de la noticia
                String creador = elemento.getElementsByTagName("dc:creator").item(0).getTextContent();

                System.out.println("Título de la noticia: " + tituloNoticia);
                System.out.println("Atributo: " + atributoMedia);
                System.out.println("Creador: " + creador);
                System.out.println("-------------------------");
            }
        }
    }
}
