/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneko;

import com.eneko.hibernate.HibernateUtil;
import com.eneko.hibernate.Libro;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author eneko
 */

/*
    Muestra el ISBN, titulo y nombre del autor de todos los libros
    Muestra toda la info de los libros, autores y editoriales medienate joins
    Consulta mediante input de usuario
*/
public class Main {
    
    private final static Scanner SC = new Scanner(System.in);
    
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        String hql = "FROM Libro";
        Query query = session.createQuery(hql);
        
        List<Libro> lista = query.list();
        Iterator<Libro> iterator = lista.iterator();
        
        while(iterator.hasNext()) {
            Libro libro = (Libro) iterator.next();
            System.out.printf(
                "Titulo: %s\n\tISBN: %s\n\tAutor: %s\n", 
                libro.getTitulo(), libro.getIsbn(), libro.getAutor().getNombreAutor()
            );
        }
        
        System.out.println("Id del autor:");
        int autorId = SC.nextInt();
        System.out.println("Id de la editorial:");
        int editorialId = SC.nextInt();
        System.out.println("Id del tema:");
        int temaId = SC.nextInt();
        
        hql = "SELECT l FROM Libro l " +
                  "JOIN l.autor a " +
                  "JOIN l.editorial e " +
                  "JOIN l.tema t " +
                  "WHERE a.idAutor = :nombreAutor " +
                  "AND e.idEditorial = :nombreEditorial " +
                  "AND t.idTema = :nombreTema";
        
        query = session.createQuery(hql);
        query.setParameter("nombreAutor", autorId);
        query.setParameter("nombreEditorial", editorialId);
        query.setParameter("nombreTema", temaId);
        
        List<Libro> lista2 = query.list();
        Iterator<Libro> iterator2 = lista2.iterator();
        
        while(iterator2.hasNext()) {
            Libro libro = (Libro) iterator2.next();
            System.out.printf(
                "Titulo: %s\n\t"
                        + "ISBN: %s\n\t"
                        + "Numero de Ejemplares: %d\n\t"
                        + "Autor: %s\n\t"
                        + "Editorial: %s\n\t"
                        + "Tema: %s\n", 
                libro.getTitulo(),
                libro.getIsbn(), 
                libro.getNumeroEjemplares(), 
                libro.getAutor().getNombreAutor(),
                libro.getEditorial().getNombreEditorial(),
                libro.getTema().getNombreTema()
            );
        }
        SC.close();
        sessionFactory.close();
        if (session.isConnected()) session.close();
    }
}
