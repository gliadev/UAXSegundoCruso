/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tema2.ejer002;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author damiansualdea
 */
public class Enfermero extends Thread{
    private String nombre;
    private ListaPaciente lista;
    private long inicio;

    public Enfermero(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public Enfermero(String nombre, ListaPaciente lista, long inicio) {
        this.nombre = nombre;
        this.lista = lista;
        this.inicio = inicio;
    }

    

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaPaciente getLista() {
        return lista;
    }

    public void setLista(ListaPaciente lista) {
        this.lista = lista;
    }

    public long getInicio() {
        return inicio;
    }

    public void setInicio(long inicio) {
        this.inicio = inicio;
    }

   

    

    
    
    private void tiempoVacunacion(int segundos) throws InterruptedException{
        Thread.sleep(segundos*1000);
    }
    
   /* public void vacunar(ListaPaciente list, long timeStamp) throws InterruptedException{
        System.out.println("El enfermero: "+nombre + " empieza "
                +list.getNombre()+ " Tiempo: "+((System.currentTimeMillis()-timeStamp)/1000));
        
        for(int i = 0; i< list.getTiempoPacientes().length;i++){
            tiempoVacunacion(list.getTiempoPacientes()[i]);
            System.out.println("Vacunando al paciente "+(i+1) + 
                    " -> Tiempo: "+(System.currentTimeMillis()-timeStamp)/1000);
        }
        
        System.out.println("El enfermero: "+nombre + " Ha TERMINADO "
                +(System.currentTimeMillis()-timeStamp)/1000+" segundos");
    }
    */
    
    @Override
    public void run() {
        System.out.println("El enfermero: "+nombre + " empieza "
                +lista.getNombre()+ " Tiempo: "+((System.currentTimeMillis()-inicio)/1000));
        
        for(int i = 0; i< lista.getTiempoPacientes().length;i++){
            try {
                this.tiempoVacunacion(lista.getTiempoPacientes()[i]);
            } catch (InterruptedException ex) {
                Logger.getLogger(Enfermero.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(nombre+" Vacunando al paciente "+(i+1) + 
                    " -> Tiempo: "+(System.currentTimeMillis()-inicio)/1000);
        }
        
        System.out.println("El enfermero: "+nombre + " Ha TERMINADO "
                +(System.currentTimeMillis()-inicio)/1000+" segundos");
    }
    
}
