package reto02;

import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gliatem
 */
public class MarvelHeroes {
    
    private String hero;
    private ArrayList<Hero> heroes;
    
    
    // Constructor 
     public MarvelHeroes(String hero) {
        this.hero = hero;
        heroes = new ArrayList<Hero>();
    }
    
    // Add y Remove

    public boolean add(Hero e) {
        return heroes.add(e);
    }

    public Hero remove(int index) {
        return heroes.remove(index);
    }
     

   

    
}
