/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package leerxml;

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
public class LeerXML {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        //Crear objeto DocumentBuilder
        DocumentBuilderFactory factory = 
                DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = 
                factory.newDocumentBuilder();
        Document document = builder.parse(new File("Empleados.xml"));
   
        document.getDocumentElement().normalize();

        //Extraer nodo raiz
        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());
        //transformación a una estructura de datos de Java
        //es NodeList
        NodeList nList = document.getElementsByTagName("empleado");
        System.out.println("=========================================");
        System.out.println(nList.getLength());
        Node nodo;
        for (int i = 0; i < nList.getLength(); i++) {
            nodo = nList.item(i);
            System.out.println("Inicio del empleado");
            System.out.println("");
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) nodo;
                System.out.println("Su id es: " + elemento.getAttribute("id"));
                System.out.println("Apellido: " + elemento.getElementsByTagName("apellidos").item(0).getTextContent());
                System.out.println("----------");
                System.out.println("Nombre: " + elemento.getElementsByTagName("nombre").item(0).getTextContent());
                System.out.println("===========");
                System.out.println("Ciudad: " + elemento.getElementsByTagName("ciudad").item(0).getTextContent());
                if (elemento.getElementsByTagName("ciudad").item(0).getTextContent().equals("Madrid")){
                    System.out.println("ES DE MADRIZ");
                }
               // System.out.println("Pais: " + elemento.getElementsByTagName("pais").item(0).getTextContent());
            }
        }

    }
}
