/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicandojson;

/**
 *
 * @author gliatem
 */
public class Producto {
    
    private String nombre;
    private String fabricante;
    private String link;
    private String img;
    private int precio;
    
    
    // Constructor por defecto con todos los parametros
    public Producto(String nombre, String fabricante, String link, String img, int precio) {
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.link = link;
        this.img = img;
        this.precio = precio;
    }
    
    // Geters y Seters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    // metodo toString

    @Override
    public String toString() {
        return "Producto{" + "nombre=" + nombre + ", fabricante=" + fabricante + ", link=" + link + ", img=" + img + ", precio=" + precio + '}';
    }
    
    
}
