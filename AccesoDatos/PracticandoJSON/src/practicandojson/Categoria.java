/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicandojson;

import java.util.ArrayList;

/**
 *
 * @author gliatem
 */
public class Categoria {
    
    private String nombre;
    private ArrayList<Producto> productos;
    
    // constructor con instancia del ArrayList
    public Categoria(String nombre) {
        this.nombre = nombre;
        productos = new ArrayList<Producto>();
    }
    
    
    // Add y Remove metodos delegados
    public boolean add(Producto e) {
        return productos.add(e);
    }

    public Producto remove(int index) {
        return productos.remove(index);
    }
    
}
