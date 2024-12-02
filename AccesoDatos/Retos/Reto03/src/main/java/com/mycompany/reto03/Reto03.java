/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.reto03;
import java.sql.*;

/**
 *
 * @author gliaDev
 */

public class Reto03 {
    
    // URL de la base de datos
    private static final String url = "jdbc:mysql://localhost:3306/biblioteca";

    public static void main(String[] args) throws SQLException {
        // Credenciales de acceso
        String user = "user-biblioteca";
        String password = "ChuckNorris-20024";
        
        // Conexión a la base de datos usando try-with-resources 😉
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement sentencia = con.createStatement()) {
            
            System.out.println("Conexión a base de datos. OK");

            // Visualizar libros existentes
            visualizarLibros(con);

            // Datos del libro a insertar
            String isbn = "HP1234"; // Código único del libro
            String titulo = "Harry Potter y la Piedra Filosofal";
            int numeroEjemplares = 100; // Ejemplares disponibles
            String autor = "J.K. Rowling";
            String nombreEditorial = "Bloomsbury";
            String nombreTema = "Fantasía";

            // Verificar si el libro ya existe
            String selectLibro = "SELECT * FROM libro WHERE isbn='" + isbn + "'";
            ResultSet sentenciaSelect = sentencia.executeQuery(selectLibro);
            if (sentenciaSelect.next()) {
                System.out.println("El libro ya existe en la base de datos.");
            } else {
                System.out.println("El libro no existe, vamos aniadirlo...");

                // Alta del autor
                if (!existeAutor(autor, con)) {
                    altaAutor(autor, con); // Insertar autor con PreparedStatement
                }

                // Alta del tema
                if (!existeTema(nombreTema, con)) {
                    altaTema(nombreTema, con); // Insertar tema con PreparedStatement
                }

                // Alta de la editorial
                if (!existeEditorial(nombreEditorial, con)) {
                    altaEditorial(nombreEditorial, con); // Insertar editorial con CallableStatement
                }

                // Insertar el libro
                String insertLibro = "INSERT INTO libro (isbn, titulo, numeroEjemplares, idAutor, idEditorial, idTema) "
                                    + "VALUES ('" + isbn + "', '" + titulo + "', " + numeroEjemplares + ", 1, 1, 1)";
                sentencia.executeUpdate(insertLibro);
                System.out.println("Libro insertado correctamente con Statement.");
            }
        }
    }

    // Método para visualizar los libros
    private static void visualizarLibros(Connection con) {
        try (Statement sentencia = con.createStatement()) {
            String sql = "SELECT * FROM libro NATURAL JOIN (autor, tema, editorial)";
            ResultSet salida = sentencia.executeQuery(sql);
            System.out.println("Listado de libros disponibles:");
            while (salida.next()) {
                System.out.print("ISBN: " + salida.getString("isbn") + ", ");
                System.out.print("Título: " + salida.getString("titulo") + ", ");
                System.out.print("Autor: " + salida.getString("nombreAutor") + ", ");
                System.out.print("Tema: " + salida.getString("nombreTema") + ", ");
                System.out.println("Editorial: " + salida.getString("nombreEditorial"));
            }
        } catch (SQLException e) {
            System.out.println("Error en visualizar libros.");
        }
    }

    // Verificar si un autor ya existe
    private static boolean existeAutor(String autor, Connection con) throws SQLException {
        String selectAutor = "SELECT idAutor FROM autor WHERE nombreAutor = '" + autor + "'";
        ResultSet salida = con.createStatement().executeQuery(selectAutor);
        return salida.next();
    }

    // Insertar un nuevo autor
    private static void altaAutor(String autor, Connection con) {
        String alta = "INSERT INTO autor (nombreAutor) VALUES (?)";
        try (PreparedStatement insert = con.prepareStatement(alta)) {
            insert.setString(1, autor);
            insert.executeUpdate();
            System.out.println("Autor '" + autor + "' dado de alta correctamente con PreparedStatement.");
        } catch (SQLException e) {
            System.out.println("Error en el alta del autor.");
        }
    }

    // Verificar si un tema ya existe
    private static boolean existeTema(String tema, Connection con) throws SQLException {
        String selectTema = "SELECT idTema FROM tema WHERE nombreTema = '" + tema + "'";
        ResultSet salida = con.createStatement().executeQuery(selectTema);
        return salida.next();
    }

    // Insertar un nuevo tema
    private static void altaTema(String tema, Connection con) {
        String alta = "INSERT INTO tema (nombreTema) VALUES (?)";
        try (PreparedStatement insert = con.prepareStatement(alta)) {
            insert.setString(1, tema);
            insert.executeUpdate();
            System.out.println("Tema '" + tema + "' dado de alta correctamente con PreparedStatement.");
        } catch (SQLException e) {
            System.out.println("Error en el alta del tema.");
        }
    }

    // Verificar si una editorial ya existe
    private static boolean existeEditorial(String editorial, Connection con) throws SQLException {
        String selectEditorial = "SELECT idEditorial FROM editorial WHERE nombreEditorial = '" + editorial + "'";
        ResultSet salida = con.createStatement().executeQuery(selectEditorial);
        return salida.next();
    }

    // Insertar una nueva editorial usando CallableStatement
    private static void altaEditorial(String editorial, Connection con) {
        try (CallableStatement cstmt = con.prepareCall("{CALL alta_editorial(?, ?)}")) {
            cstmt.setInt(1, 1); // ID de la editorial (puedes ajustar según la lógica de tu base de datos)
            cstmt.setString(2, editorial);
            cstmt.execute();
            System.out.println("Editorial '" + editorial + "' dada de alta correctamente con CallableStatement.");
        } catch (SQLException e) {
            System.out.println("Error en el alta de la editorial.");
        }
    }
}