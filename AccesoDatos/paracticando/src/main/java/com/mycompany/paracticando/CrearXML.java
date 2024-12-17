/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.paracticando;

import java.io.File;
import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;





/**
 *
 * @author gliatem
 */
public class CrearXML {

    public static void main(String[] args) {
        
        try {
             // 1. Definir el nombre del archivo
            String nombreFichero = "productos.xml";
            File fichero = new File(nombreFichero);

            // 2. Crear una instancia de DocumentBuilder
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();

            // 3.  Inicializar un documento XML vacío
            Document doc = dbBuilder.newDocument();

            // 4.  Crear el nodo raíz <productos>
            Element rootElement = doc.createElement("productos");
            doc.appendChild(rootElement);

            // 5. Configurar el transformador para guardar el archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // 6. Guardar el archivo XML en el sistema
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(fichero));
            transformer.transform(source, result);

            System.out.println("Archivo XML creado correctamente: " + nombreFichero);
            
        } catch(Exception e){
            e.printStackTrace();
        }
    
    } 
}

