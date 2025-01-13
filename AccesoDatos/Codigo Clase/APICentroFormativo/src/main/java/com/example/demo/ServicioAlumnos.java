/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.example.demo;

import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author damiansualdea
 */
@Service
public class ServicioAlumnos {
    private final RepositorioAlumnos repositorioAlumnos;

    public ServicioAlumnos(RepositorioAlumnos repositorioAlumnos) {
        this.repositorioAlumnos = repositorioAlumnos;
    }
    
    public List<Alumnos> obtenerAlumnos(){
        return repositorioAlumnos.findAll();
    }
}
