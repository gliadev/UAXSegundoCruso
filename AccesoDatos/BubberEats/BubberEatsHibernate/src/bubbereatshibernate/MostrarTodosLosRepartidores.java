/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubbereatshibernate;
import bubbereatshibernate.Repartidores;


import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Adolfo
 */
public class MostrarTodosLosRepartidores {
    public static void main(String[] args) {
      Session session = null;
        Transaction tx = null;

        try {
            // Abrir la sesión
            session = HibernateUtil.getSessionFactory().openSession();
            // Iniciar la transacción
            tx = session.beginTransaction();

            // Consulta HQL
            String hql = "FROM Repartidores";
            List<Repartidores> repartidores = session.createQuery(hql).list();

            // Mostrar resultados
            for (Repartidores repartidor : repartidores) {
                System.out.println("ID: " + repartidor.getIdRepartidor() +
                                   ", Nombre: " + repartidor.getNombreRepartidor() +
                                   ", Teléfono: " + repartidor.getTelefono());
            }

            // Confirmar la transacción
            tx.commit();
        } catch (Exception e) {
            // Revertir la transacción en caso de error
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error al mostrar repartidores: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar la sesión
            if (session != null) {
                session.close();
            }
        }
    }
}
 