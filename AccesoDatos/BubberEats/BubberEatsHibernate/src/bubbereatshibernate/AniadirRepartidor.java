/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubbereatshibernate;
import java.util.Scanner;
import org.hibernate.*;
/**
 *
 * @author Adolfo
 */

public class AniadirRepartidor {
    public static void main(String[] args) {
        
        // Crear un escáner para leer datos de la consola
        try (Scanner Teclado = new Scanner(System.in)) {
            // Configurar Hibernate
            SessionFactory sesion = HibernateUtil.getSessionFactory();
            Session session = null;
            Transaction tx = null;

            try {
                // Abrir la sesión y la transacción
                session = sesion.openSession();
                tx = session.beginTransaction();

                // Solicitar datos al usuario
                System.out.println("Agregando un nuevo Repartidor");
                System.out.print("Introduce el nombre del repartidor: ");
                String nombreRepartidor = Teclado.nextLine();

                System.out.print("Introduce el teléfono del repartidor: ");
                String telefonoRepartidor = Teclado.nextLine();

                // Crear un nuevo repartidor con los datos ingresados
                Repartidores Nuevorepartidor = new Repartidores();
                Nuevorepartidor.setNombreRepartidor(nombreRepartidor);
                Nuevorepartidor.setTelefono(telefonoRepartidor);

                // Guardar en la base de datos
                session.save(Nuevorepartidor);
                tx.commit();

                // Confirmación de éxito
                System.out.println("El repartidor " + Nuevorepartidor.getNombreRepartidor() + " ha sido insertado en la base de datos.");
            } catch (HibernateException e) {
                // Manejar errores
                if (tx != null) {
                    tx.rollback(); // Revertir la transacción en caso de error
                }
                System.out.println("Error al insertar el repartidor: " + e.getMessage());
            }

            // Cerrar la sesión de manera explícita si se abrió correctamente
            if (session != null && session.isOpen()) {
                session.close();
                System.out.println("La sesión se ha cerrado correctamente.");
            }

            // Cerrar la fábrica de sesiones de manera explícita
            if (sesion != null && !sesion.isClosed()) {
                sesion.close();
                System.out.println("La fábrica ha cerrado correctamente.");
            }
        } catch (Exception e) {
            System.out.println("Error general: " + e.getMessage());
        }
    }
}
