/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package reto02;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author gliatem
 */
public class Reto02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // Crear la estructura para héroes
        JSONArray heroesArray = new JSONArray();  // Array para almacenar héroes

        // Scanner para recibir datos desde consola
        Scanner scanner = new Scanner(System.in);
        String input = "";
        boolean isRunning = true;

        System.out.println("Introduce la información de los héroes. Para terminar, introduce '*'.");

        // Bucle controlado por la variable `isRunning`
        while (isRunning) {
            System.out.print("Nombre del héroe (o '*' para terminar): ");
            input = scanner.nextLine();
            
            // Cambiar la condición a `isRunning = false` cuando se introduce `*`
            if (input.equals("*")) {
                isRunning = false;
                continue; // Saltar al final del bucle si se ingresa '*'
            }

            String heroName = input;

            System.out.print("Nombre real del héroe: ");
            String realName = scanner.nextLine();

            System.out.print("Enlace del héroe (opcional): ");
            String link = scanner.nextLine();

            System.out.print("URL de la imagen: ");
            String img = scanner.nextLine();

            System.out.print("Tamaño (número entero): ");
            int size = Integer.parseInt(scanner.nextLine());

            // Crear un nuevo JSONObject para el héroe y añadirlo al JSONArray
            JSONObject heroObject = new JSONObject();
            heroObject.put("hero", heroName);
            heroObject.put("name", realName);
            heroObject.put("link", link);
            heroObject.put("img", img);
            heroObject.put("size", size);

            heroesArray.put(heroObject);
        }

        // Crear el objeto principal que contiene la colección de héroes
        JSONObject marvelHeroes = new JSONObject();
        marvelHeroes.put("hero", "Heroes");
        marvelHeroes.put("heroes", heroesArray);

        // Convertir a String el JSON para mostrarlo
        String jsonOutput = marvelHeroes.toString(4); // El parámetro 4 es para una mejor indentación

        // Mostrar el JSON generado
        System.out.println("\nJSON generado:");
        System.out.println(jsonOutput);

        // Guardar el JSON en un archivo
        saveJsonToFile(jsonOutput);

        // Leer y mostrar el contenido del archivo JSON guardado
        readJsonFromFile();

        scanner.close();
    }

    // Método para guardar JSON en un archivo
    private static void saveJsonToFile(String json) {
        try (FileWriter file = new FileWriter("heroes.json")) {
            file.write(json);
            System.out.println("\nArchivo JSON guardado correctamente como 'heroes.json'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para leer y mostrar JSON desde un archivo
    private static void readJsonFromFile() {
        System.out.println("\nLeyendo el contenido del archivo 'heroes.json':");
        try (FileReader reader = new FileReader("heroes.json");
             BufferedReader bufferedReader = new BufferedReader(reader)) {
             
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            System.out.println(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
