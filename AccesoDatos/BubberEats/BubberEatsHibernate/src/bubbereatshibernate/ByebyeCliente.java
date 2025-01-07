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
        int idCliente = 2;

        // Declarar sesión y transacción
        Session session = null;
        Transaction tx = null;

        try {
            // Abrir la sesión
            session = HibernateUtil.getSessionFactory().openSession();

            // Iniciar transacción
            tx = session.beginTransaction();

            // Llamar al procedimiento almacenado 'byebyeCliente'
            session.createSQLQuery("CALL byebyeCliente(:idCliente)")
                   .setParameter("idCliente", idCliente)
                   .executeUpdate();

            // Confirmar la transacción
            tx.commit();
            System.out.println("El cliente con ID " + idCliente + " ha sido eliminado correctamente.");
        } catch (Exception e) {
            // Revertir la transacción en caso de error
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error al eliminar el cliente: " + e.getMessage());
        } finally {
            // Cerrar la sesión si se abrió correctamente
            if (session != null) {
                session.close();
            }
        }
    }
}