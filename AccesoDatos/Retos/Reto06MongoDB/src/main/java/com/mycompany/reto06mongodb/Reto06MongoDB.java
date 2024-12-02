/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.reto06mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author gliatem
 */
public class Reto06MongoDB {

    public static void main(String[] args) {
       
         // Usamos try-with-resources para manejar automáticamente el cierre de recursos
        try (MongoClient cliente = new MongoClient("localhost", 27017)) {
            // Conexión a la base de datos
            MongoDatabase miBaseDeDatos = cliente.getDatabase("mibasedatos");

            // Obtener la colección 'empleados' de la base de datos
            MongoCollection<Document> empleados = miBaseDeDatos.getCollection("empleados");

            // Confirmación de conexión y colección
            System.out.println("Conectado a MongoDB");
            System.out.println("Conexion correcta a la base de datos: " + miBaseDeDatos.getName());
            System.out.println("Coleccion seleccionada: " + empleados.getNamespace());
        
        // Obtengo todos el documento de la coleccion
        List<Document> consultaDeTodoLoQueTenemos = empleados.find().into(new ArrayList <Document>());
        
        
        
        // INSERT empleados
        // datos del emmpleado y lo metemos con insertOne() 
        Document aniadirEmpleadoDamian = new Document();
        aniadirEmpleadoDamian.put("nombre", "Damian");
        aniadirEmpleadoDamian.put("dep", 76);
        aniadirEmpleadoDamian.put("salario", 3500);
        aniadirEmpleadoDamian.put("oficio", "Formador exHacker");
        empleados.insertOne(aniadirEmpleadoDamian);
        // Confirmar que el empleado fue añadido
        System.out.println("Empleado añadido: " + aniadirEmpleadoDamian);
       
        
        // Añadir un personaje de Star Wars - Obi-Wan Kenobi 
        Document aniadirEmpleadoObiWan = new Document("nombre", "Obi-Wan Kenobi")
            .append("dep", 89)
            .append("salario", 5000)
            .append("oficio", "Jedi Master");

        // Insertar el documento en la colección
        empleados.insertOne(aniadirEmpleadoObiWan);

        // Confirmar que el personaje fue añadido
        System.out.println("Personaje añadido: " + aniadirEmpleadoObiWan);

        // Añadir varios personajes de Star Wars con insertMany
        List<Document> personajesStarWars = Arrays.asList(
                new Document("nombre", "Luke Skywalker")
                    .append("dep", 101)
                    .append("salario", 7000)
                    .append("oficio", "Jedi Knight"),

                new Document("nombre", "Leia Organa")
                    .append("dep", 102)
                    .append("salario", 8000)
                    .append("oficio", "General"),

                new Document("nombre", "Han Solo")
                    .append("dep", 103)
                    .append("salario", 6000)
                    .append("oficio", "Capitán del Halcón Milenario")
        );
            // Insertar los documentos en la colección
            empleados.insertMany(personajesStarWars);

            // Confirmar que los personajes fueron añadidos
            System.out.println("Personajes añadidos: " + personajesStarWars);
            
           // UPDATE
            // Añadir campo "edad" a "Damian" y aumentar salario de "Luke Skywalker"
            // Actualizar el salario de "Damian" a 4000 usando $set
            empleados.updateOne(new Document("nombre", "Damian"), new Document("$set", new Document("salario", 4000)));
            System.out.println("Empleado actualizado: Damian con salario de 4000");

            // Añadir campo "edad" a "Damian" usando $set
            empleados.updateOne(new Document("nombre", "Damian"), new Document("$set", new Document("edad", 24)));
            System.out.println("Empleado actualizado: Damian con edad de 24");
            System.out.println("Empleado actualizado: Damian con edad de 24");

            // Incrementar el salario de "Luke Skywalker" en 1000 usando $inc
            empleados.updateOne(new Document("nombre", "Luke Skywalker"), new Document("$inc", new Document("salario", 1000)));
            System.out.println("Empleado actualizado: Luke Skywalker con incremento de salario de 1000");
            System.out.println("Empleado actualizado: Luke Skywalker con incremento de salario de 1000");
            System.out.println("Empleado actualizado: Damian con salario de 4000");

            // Actualizar varios documentos
            // Actualizar el oficio de todos los Jedi a "Maestro Jedi"
            Bson filtroActualizarOficioJediKnight = Filters.eq("oficio", "Jedi Knight");
            Bson actualizacionOficioJedi = Updates.set("oficio", "Maestro Jedi");
            empleados.updateMany(filtroActualizarOficioJediKnight, actualizacionOficioJedi);
            System.out.println("Todos los Jedi han sido ascendidos a Maestro Jedi");

            // DELETE
            // Añadir eliminación por salario específico
            // Eliminar a "Han Solo" de la colección usando deleteOne
            empleados.deleteOne(new Document("nombre", "Han Solo"));
            System.out.println("Empleado eliminado: Han Solo");

            // Eliminar a un empleado cuyo salario sea exactamente 8000 usando deleteOne
            empleados.deleteOne(new Document("salario", 8000));
            System.out.println("Empleado con salario de 8000 eliminado");
            System.out.println("Empleado con salario de 8000 eliminado");
            System.out.println("Empleado eliminado: Han Solo");

            // Eliminar varios documentos
            // Eliminar a todos los empleados cuyo salario sea menor a 5000 usando deleteMany
            empleados.deleteMany(new Document("salario", new Document("$lt", 5000)));
            System.out.println("Empleados con salario menor a 5000 han sido eliminados");
            System.out.println("Empleados con salario menor a 5000 han sido eliminados"); 
            
        // READ
        for (Document empleado : consultaDeTodoLoQueTenemos) {
                imprimirDatosEmpleado(empleado);
            }
        
        
        } catch (Exception e) {
            // Manejo de errores
            System.err.println("Error en la conexión: " + e.getMessage());
        }
    }
        
           // Método para imprimir la información de un empleado
           private static void imprimirDatosEmpleado(Document empleado) {
                System.out.println("id Emp: " + empleado.getObjectId("_id"));
                System.out.println("Emp_no: " + empleado.getInteger("Emp_no"));
                System.out.println("Nombre: " + empleado.getString("nombre"));
                System.out.println("Departamento: " + empleado.getInteger("dep"));
                System.out.println("Salario: " + empleado.getInteger("salario"));
                System.out.println("Fecha Alta: " + empleado.getString("fechaalta"));
                System.out.println("Oficio: " + empleado.getString("oficio"));
                System.out.println("---------------------------------");
    }
}
    


    

