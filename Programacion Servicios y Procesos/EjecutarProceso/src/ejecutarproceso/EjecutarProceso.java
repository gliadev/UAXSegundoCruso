/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejecutarproceso;

/**
 *
 * @author gliatem
 */
public class EjecutarProceso {
    public void ejecutar(String ruta){
    ProcessBuilder pb;
        try {
            pb = new ProcessBuilder(ruta);
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
        String ruta = "calc.exe";
        EjecutarProceso lp = new EjecutarProceso();
        lp.ejecutar(ruta);
        System.out.println("Finalizado");
        
    }
}
