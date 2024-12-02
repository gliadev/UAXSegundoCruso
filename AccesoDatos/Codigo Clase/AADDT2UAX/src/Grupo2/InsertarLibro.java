package Grupo2;

import java.sql.*;

public class InsertarLibro {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/biblioteca";
        String usuario = "user-biblioteca";
        String password = "ChuckNorris-20024";

        try ( Connection con
                = DriverManager.getConnection(url, usuario, password);  Statement sentencia = con.createStatement();) {
            
            visualizarLibros(sentencia);
            System.out.println("Hablamos con el usuario y pedidos datos");
            
            
            /* Deberíamos de gestionar
            String autor = "";
            String editorial = "";
            String tema=""; 
            */
            
            //Inserción FALSA... en el mejor de los casos
            /*String titulo = "Victoria-falso";
            String isbn = "Falso ISBN978-";
            int numeroEjemplares = 50;
            int autor = 2;
            int editorial = 4;
            int tema = 4;
            String sentenciaInsert = "INSERT INTO libro "
                    + "VALUES(null,'"+isbn+"','"+titulo+"',"+numeroEjemplares+",'"+autor+"','"+editorial+"','"+tema+"')";
            sentencia.executeUpdate(sentenciaInsert);
            System.out.println("guay, isnerción falsa");
            */
            
            String autor = "";
            String editorial = "";
            String tema="";
            String isbn = "";
            int numeroEjemplares = 50;
            //Si existe o no el ISBN
            
            //Si no existe algo... lo creamos...
            //Si existe algo... 
            //nos quedamos con su ID
            //al final la inserción
            
            
            
        } catch (SQLException e) {
            System.out.println("Error en INSERT");
            System.out.println(e.getMessage());
        }

    }

    private static void visualizarLibros(final Statement sentencia) throws SQLException {
        String sentenciaSelect = "SELECT * FROM libro NATURAL JOIN(AUTOR,EDITORIAL,TEMA);";
        ResultSet cursor = sentencia.executeQuery(sentenciaSelect);

        while (cursor.next()) {
            String isbn = cursor.getString("ISBN");

            String titulo = cursor.getString("titulo");
            int numeroEjemplares = cursor.getInt("numeroEjemplares");
            System.out.print(isbn + " ");
            System.out.print(titulo + " ");
            System.out.print(numeroEjemplares + " ");
            System.out.println("");
        }
    }
}
