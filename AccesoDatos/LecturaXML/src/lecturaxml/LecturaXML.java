/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lecturaxml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;




/**
 *
 * @author gliatem
 */
/**
 *
 * @author gliatem
 */
public class LecturaXML {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
         // 1ª Paso: Crear la fábrica de constructores de documentos
        // Esto inicializa una instancia de DocumentBuilderFactory para configurar el analizador XML.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        // 2ª Paso: Crear un constructor de documentos a partir de la fábrica
        // DocumentBuilder es una clase que permite la construcción de documentos DOM.
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        // 3ª Paso: Cargar y parsear el archivo XML especificado
        // Aquí cargamos el archivo XML y lo parseamos en un objeto Document.
        Document document = builder.parse(new File("src/lecturaxml/campa.xml"));
        
        // 4ª Paso: Normalizar el documento XML
        // La normalización garantiza que el documento esté en un formato uniforme, eliminando nodos redundantes.
        document.getDocumentElement().normalize();
        
        // 5ª Paso: Obtener el elemento raíz del XML
        // document.getDocumentElement() devuelve el elemento raíz del XML, en este caso "campa".
        Element root = document.getDocumentElement();
        
        // Mostrar el nombre del nodo raíz y el valor del atributo "nombre"
        System.out.println(root.getNodeName() + ": " + root.getAttribute("nombre"));
        System.out.println(); // Línea en blanco para separar la salida

        // 6ª Paso: Obtener todos los nodos "coche" dentro del nodo raíz "campa"
        // getElementsByTagName devuelve todos los elementos con el tag "coche" en forma de NodeList.
        NodeList nList = root.getElementsByTagName("coche");
        System.out.println("Coches en la campa: " + nList.getLength());
        System.out.println("======================");
        System.out.println(); // Línea en blanco para separar la salida

        // 7ª Paso: Recorrer cada elemento "coche" en el NodeList
        for (int i = 0; i < nList.getLength(); i++) {
            // Obtenemos el elemento "coche" en la posición i
            Element coche = (Element) nList.item(i);
            
            // Mostrar el atributo "matricula" del elemento "coche"
            System.out.println("Matrícula: " + coche.getAttribute("matricula"));
            
            // Obtener y mostrar el contenido del elemento "modelo"
            System.out.println("Modelo: " + coche.getElementsByTagName("modelo").item(0).getTextContent());
            
            // Obtener y mostrar el contenido del elemento "distintivo"
            System.out.println("Distintivo: " + coche.getElementsByTagName("distintivo").item(0).getTextContent());
            
            // Obtener el elemento "combustible" y su contenido de texto
            Element combustible = (Element) coche.getElementsByTagName("combustible").item(0);
            System.out.println("Combustible: " + combustible.getTextContent());
            
            // Mostrar el atributo "litro" del elemento "combustible"
            System.out.println("Litros: " + combustible.getAttribute("litro"));
            System.out.println(); // Línea en blanco para separar cada coche
        }
    }
}