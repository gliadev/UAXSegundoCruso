/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reto02sincronizaciondehilos;

/**
 *
 * @author gliatem
 */
public class Sumatorio extends Thread {
    
    private int numero;  // Número hasta el cual se calcularán los impares
    private double resultado;  // Resultado de la suma de impares

    // Constructor: recibe el número sobre el cual hará el cálculo
    public Sumatorio(int numero) {
        this.numero = numero;
    }

    /**
    * Método que ejecuta el cálculo en un hilo
    * Tenia sin @Override y el Netbeans estaba todo el rato diciendo que me tenia que 
    * ponerlo con @Override, revisado apuntes y al GPTo y me decia que al estar sobreescribiendo
    * el metodo run() de la clase Thread era mas que recomendable para evitar errores al escribir
    * los numeros mal o evitar errores a futuro.
    * 
    **/
    
    
    @Override
    public void run() {
        resultado = sumaImpar(); // Ejecuto el cálculo y guardo el resultado
    }

    // Método que calcula la suma de los números impares hasta el número dado
    public double sumaImpar() {
        // Si el número es par, lo ajustamos al impar anterior
        if (numero % 2 == 0) {
            numero -= 1;
        }

        double total = 0; // Inicializamos la suma
        // Sumamos los números impares desde el número dado hasta 1
        for (int i = numero; i >= 1; i -= 2) {
            total += i;
        }
        return total; // Retornamos el resultado
    }

    // Método para obtener el resultado después de que el hilo termine
    public double getResultado() {
        return resultado;
    }
}
