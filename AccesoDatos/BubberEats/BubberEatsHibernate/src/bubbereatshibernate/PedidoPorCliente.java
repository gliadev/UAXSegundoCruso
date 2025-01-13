/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubbereatshibernate;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Adolfo
 */
public class PedidoPorCliente {
    public static void main(String[] args) {
         // Crear SessionFactory e inicializar variables
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction tx = null;

        // Leer el ID del cliente desde el teclado
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce el ID del cliente: ");
        int idCliente = teclado.nextInt();

        // Manejo manual de sesión y transacción
        session = sessionFactory.openSession();
        tx = session.beginTransaction();

        try {
            // HQL para obtener los pedidos por cliente
            String hql = "FROM Pedidos p WHERE p.cliente.id = :idCliente";
Query query = session.createQuery(hql);
query.setParameter("idCliente", idCliente);
List<Pedidos> pedidos = query.list();

            

            // Mostrar los pedidos
            if (pedidos.isEmpty()) {
                System.out.println("El cliente con ID " + idCliente + " no tiene pedidos.");
            } else {
                System.out.println("Pedidos realizados por el cliente con ID " + idCliente + ":");
                for (Pedidos pedido : pedidos) {
                    System.out.println(pedido);
                }
            }

            // Confirmar la transacción
            tx.commit();
        } catch (Exception e) {
            // Revertir la transacción si ocurre un error
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al obtener los pedidos: " + e.getMessage());
        }

        // Verificar y cerrar sesión manualmente
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}