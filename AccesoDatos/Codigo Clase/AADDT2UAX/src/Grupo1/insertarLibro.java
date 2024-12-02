package Grupo1;

import java.sql.*;

public class insertarLibro {

    private static final String url = "jdbc:mysql://localhost:3306/biblioteca";

    public static void main(String[] args) throws SQLException {
        String user = "user-biblioteca";
        String password = "ChuckNorris-20024";
        try ( Connection con = DriverManager
                .getConnection(url, user, password);
                Statement sentencia = con.createStatement();) {
            con.createStatement();

            visualizarLibros(con);
            //Insertar un libro, Datos del libro
            String isbn = "1234";
            String titulo = "Victoria";
            int numeroEjemplares = 500;
            
            
            //Esto son datos... que hacen referencia a otras tablas
            String autor="Paloma Sánchez-Garnica";
            String nombreEditorial = "Planeta";
            String nombreTema = "drama";
            
            //Comprobación de datos: Libro
            String selectLibro = "SELECT * FROM libro WHERE isbn='"+isbn+"'";
            ResultSet sentenciaSelect = sentencia.executeQuery(selectLibro);
            if(sentenciaSelect.next()){
                System.out.println("El libro ya existe");
                System.out.println("");
            }else { //Damos de alta
                //empezamos con el autor
                
                if(!existeAutor(autor,con)){
                    altaAutor(autor,con);
                }
                
                //int idAutor=buscarAutor(autor);
                
                
                
                
                
                //insert into libro values("1234",
                //  "Victoria",500, idAutor , 1, 3);
            }
            

        }
    }

    private static void visualizarLibros(Connection con) {
        try ( Statement sentencia = con.createStatement();) {
            String sql
                    = "SELECT * FROM `Libro` NATURAL JOIN (Autor, Tema, Editorial)";
            ResultSet salida = sentencia.executeQuery(sql);
            while (salida.next()) {
                System.out.print(salida.getString("ISBN") + " ");
                //Hacerlo vosotros....
                
            }
        } catch (SQLException e) {
            System.out.println("Error en visualizar libro");
        }
    }
    
    
    private static boolean existeAutor(String autor,Connection con) throws SQLException{
        String selectAutor = "SELECT idAutor FROM autor"
                + " WHERE nombreAutor = '"+autor+"'";
        ResultSet salida = con.createStatement().executeQuery(selectAutor);
        return salida.next();
    }
    
    private static void altaAutor(String autor,Connection con){
        String alta = "INSERT INTO autor(nombreAutor) values (?)";
        try(PreparedStatement insert = con.prepareStatement(alta)){
            insert.setString(1, autor);
            insert.executeUpdate();
            System.out.println("Autor dado de alta correctamente");
        }catch(SQLException e){
            System.out.println("Error en el alta del autor");
        }
    }
}
