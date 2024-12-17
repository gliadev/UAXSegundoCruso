/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.paracticando;

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

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

        // 1. Crear la fábrica de constructores de documentos
        // DocumentBuilderFactory es una clase utilizada para configurar y crear
        // analizadores DOM (Document Object Model) que nos permitirán leer y manipular
        // archivos XML como objetos Java.
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // 2. Crear el constructor de documentos a partir de la fábrica
        // DocumentBuilder es responsable de analizar el archivo XML y transformarlo
        // en una estructura DOM, que es manipulable desde Java.
                DocumentBuilder builder = factory.newDocumentBuilder();

        // 3. Cargar y parsear el archivo XML
        // Aquí se carga el archivo XML desde la ruta especificada en el proyecto.
        // Nota importante: Asegúrate de que la ruta sea correcta y accesible.
        // Si el archivo no existe o la ruta está mal, lanzará un FileNotFoundException.
        File xmlFile = new File("src/main/java/com/mycompany/paracticando/productos.xml");
        Document document = builder.parse(xmlFile);

        // 4. Normalizar el documento XML
        // Este paso garantiza que el documento XML esté en un estado uniforme.
        // Por ejemplo, combina nodos de texto adyacentes y elimina nodos vacíos innecesarios.
                document.getDocumentElement().normalize();

        // 5. Obtener el elemento raíz del XML
        // getDocumentElement() devuelve el nodo raíz del documento XML.
        // En este caso, debería ser <productos> según la estructura del archivo XML.
                Element root = document.getDocumentElement();
                System.out.println("Elemento raíz: " + root.getNodeName());
                System.out.println();

        // 6. Obtener todos los nodos <producto>
        // getElementsByTagName() busca en el documento todos los nodos con el nombre
        // especificado, en este caso "producto", y devuelve una lista de ellos.
                NodeList productos = root.getElementsByTagName("producto");

        // 7. Iterar sobre cada nodo <producto>
        // Este bucle recorre todos los nodos de la lista obtenida en el paso anterior.
                for (int i = 0; i < productos.getLength(); i++) {
                    // Obtener el nodo actual de la lista
                    Node productoNode = productos.item(i);

                    // Verificar que el nodo actual sea de tipo ELEMENT_NODE
                    // Esto asegura que estamos procesando un elemento válido y no otro tipo de nodo (como comentarios o texto).
                    if (productoNode.getNodeType() == Node.ELEMENT_NODE) {
                        // Convertir el nodo en un elemento para acceder a sus atributos y subelementos
                        Element producto = (Element) productoNode;

                        // Leer atributos del producto
                        // Usamos getAttribute() para acceder al valor de un atributo en el nodo <producto>.
                        System.out.println("ID: " + producto.getAttribute("id"));

                        // Si el producto tiene un atributo "tipo", lo mostramos
                        if (producto.hasAttribute("tipo")) {
                            System.out.println("Tipo: " + producto.getAttribute("tipo"));
                        }

                        // Si el producto tiene un atributo "descuento", lo mostramos
                        if (producto.hasAttribute("descuento")) {
                            System.out.println("Descuento: " + producto.getAttribute("descuento"));
                        }

                        // Leer elementos <nombre> y <precio>
                        // getElementsByTagName() devuelve una lista de nodos con el nombre especificado.
                        // Usamos item(0) para obtener el primer (y único) nodo <nombre> y <precio>.
                        System.out.println("Nombre: " + producto.getElementsByTagName("nombre").item(0).getTextContent());

                        if (producto.getElementsByTagName("precio").getLength() > 0) {
                            System.out.println("Precio: " + producto.getElementsByTagName("precio").item(0).getTextContent());
                        }

                        // Leer elemento opcional <localizado>
                        // Verificamos si el nodo <localizado> existe antes de intentar leerlo, para evitar NullPointerException.
                        if (producto.getElementsByTagName("localizado").getLength() > 0) {
                            System.out.println("Localizado: " + producto.getElementsByTagName("localizado").item(0).getTextContent());
                        }

                        // Leer elemento opcional <detalle>
                        // Similar al caso anterior, verificamos si existe antes de intentar acceder a él.
                        if (producto.getElementsByTagName("detalle").getLength() > 0) {
                            System.out.println("Detalle: " + producto.getElementsByTagName("detalle").item(0).getTextContent());
                        }

                        // Separador para facilitar la lectura de los datos en la consola
                        System.out.println();
            }
        }
    }
}
