/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubbereatshibernate;
import org.hibernate.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Adolfo
 */
public class ByebyeCliente {
    public static void main(String[] args) {
        
     // ID del cliente a eliminar
        int idCliente = 8;

        // Crear SessionFactory e inicializar variables
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction tx = null;

        // Manejo manual de la sesión y la transacción
        session = sessionFactory.openSession();
        tx = session.beginTransaction();

         try {
            // Llamar al procedimiento almacenado 'byebyeCliente'
            session.createSQLQuery("CALL byebyeCliente(:idCliente)")
                   .setParameter("idCliente", idCliente)
                   .executeUpdate();

            // Confirmar la transacción
            tx.commit();
            System.out.println("El cliente con ID " + idCliente + " ha sido eliminado correctamente.");
        } catch (Exception e) {
            // Revertir la transacción si ocurre un error
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al eliminar el cliente: " + e.getMessage());
        }

        // Cierre de sesión fuera del bloque try-catch
        if (session != null && session.isOpen()) {
            try {
                session.close();
            } catch (Exception closeException) {
                System.err.println("Error al cerrar la sesión: " + closeException.getMessage());
            }
        }
    }
}

    