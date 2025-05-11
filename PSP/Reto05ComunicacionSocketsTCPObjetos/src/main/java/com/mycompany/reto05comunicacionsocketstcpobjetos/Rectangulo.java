/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reto05comunicacionsocketstcpobjetos;
import java.io.Serializable;
/**
 *
 * @author gliatem
 */




public class Rectangulo implements Serializable {
    private static final long serialVersionUID = 1L;

    private int lado1;
    private int lado2;
    private int area;
    private int perimetro;

    public Rectangulo(int lado1, int lado2) {
        this.lado1 = lado1;
        this.lado2 = lado2;
    }

    public void calcular() {
        this.area = lado1 * lado2;
        this.perimetro = 2 * (lado1 + lado2);
    }

    public int getLado1() { return lado1; }
    public int getLado2() { return lado2; }
    public int getArea() { return area; }
    public int getPerimetro() { return perimetro; }
}

