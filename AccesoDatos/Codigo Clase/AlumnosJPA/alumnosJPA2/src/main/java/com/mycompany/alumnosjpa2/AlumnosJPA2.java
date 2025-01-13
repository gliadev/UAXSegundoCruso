/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.alumnosjpa2;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author damiansualdea
 */
public class AlumnosJPA2 {

    public static void main(String[] args) {
        String unidadPersistencia = "com.mycompany_alumnosJPA2_jar_1.0-SNAPSHOTPU";
        EntityManagerFactory emf 
                = Persistence.createEntityManagerFactory(unidadPersistencia);
        EntityManager em = emf.createEntityManager();
        
        Alumnos a = em.find(Alumnos.class, 1);
        System.out.println(a.toString());
        
        List<Alumnos> listaAlumnos = em.createNamedQuery("Alumnos.findAll").getResultList();
        
        for (Alumnos listaAlumno : listaAlumnos) {
            System.out.println(listaAlumno.toString());
        }
        Alumnos b = (Alumnos) em.createNamedQuery("Alumnos.findById").setParameter("id", 3);
        System.out.println(b.toString());
        
        Alumnos c = (Alumnos) em.createNamedQuery("Alumnos.findByNombre").setParameter("nombre", "Rosa");
        System.out.println(a.toString());
        em.close();
        emf.close();
        
    }
}
