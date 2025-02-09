/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tema2.ejer003;

/**
 *
 * @author damiansualdea
 */
public class EntraJardin extends Thread{
    private RecursoJardin jardin;

    public EntraJardin(String name,RecursoJardin jardin) {
        super(name);
        this.jardin = jardin;
    }

    @Override
    public void run() {
        jardin.incrementaCuenta();
    }
    
    
    
    
    
}
