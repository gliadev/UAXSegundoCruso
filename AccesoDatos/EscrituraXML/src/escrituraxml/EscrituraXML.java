/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package escrituraxml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

/**
 *
 * @author gliatem
 */
public class EscrituraXML {


    public static void main(String[] args) throws FileNotFoundException {
        
        String nombreFichero = "curso";
        File fichero = new File(nombreFichero);
        
        // creamos un builder y cremos la intacia
        DocumentBuilderFactory dbFactory = DocumentBuilderFatory.newInstance();
        
        DocumentBuilder dbBuilder = dbFatory.newDocumentBuilder();
        Document doc = dbBuilder.newDocument();
        
        
        // escritura final 
        StreamResult result = new StreamResutl(new FileOutputStream(fichero))
        
    }
    
}
