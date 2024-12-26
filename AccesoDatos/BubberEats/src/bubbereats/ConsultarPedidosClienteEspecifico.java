/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubbereats;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

import java.util.List;
import java.util.Scanner;


/**
 *
 * @author Adolfo
 */
public class ConsultarPedidosClienteEspecifico {
    public static void main(String[] args) {
       // Configuración del Scanner para leer el ID del cliente
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce el ID del cliente: ");
        int clienteId = teclado.nextInt();

        // Iniciar sesión de Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            // Iniciar una transacción
            transaction = session.beginTransaction();

            // Construir la consulta HQL
            String hql = "FROM Pedido p WHERE p.cliente.idCliente = :idCliente";
            Query<Pedido> query = session.createQuery(hql, Pedido.class);
            query.setParameter("idCliente", clienteId);

            // Ejecutar la consulta y obtener resultados
            List<Pedido> pedidos = query.list();

            // Mostrar los pedidos obtenidos
            if (pedidos.isEmpty()) {
                System.out.println("No se encontraron pedidos para el cliente con ID: " + clienteId);
            } else {
                System.out.println("Pedidos realizados por el cliente con ID " + clienteId + ":");
                for (Pedido pedido : pedidos) {
                    System.out.println(pedido);
                }
            }

            // Confirmar la transacción
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar la sesión
            session.close();
        }
    }
}
