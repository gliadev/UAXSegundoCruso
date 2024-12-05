package com.gliadev.retodospersistencia

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gliadev.retodospersistencia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // 1. Declaramos la variable "binding" que nos va a permitir trabajar con los elementos de la UI
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. Inicializamos "binding" usando el layoutInflater para poder acceder a los elementos de la vista
        binding = ActivityMainBinding.inflate(layoutInflater)

        // 3. Establecemos la vista de la actividad usando "binding.root", o sea, cargamos toda la interfaz que hemos diseñado
        setContentView(binding.root)

        // 4. Configuramos el botón para guardar los datos que el usuario introduce
        binding.saveButton.setOnClickListener {
            val inputText = binding.inputText.text.toString() // Capturamos el texto que el usuario escribe
            if (inputText.isNotEmpty()) { // Solo si no está vacío
                guardarDatos(inputText) // Guardamos el texto que nos ha dado el usuario
                actualizarUI() // Actualizamos la UI para mostrar el nuevo texto guardado
                binding.inputText.text.clear() // Limpiamos el campo de entrada para que el usuario pueda escribir otro dato
            }
        }

        // Mostramos los datos guardados al abrir la aplicación para que no se pierda nada
        actualizarUI()
    }

    // Este método se encarga de guardar los datos usando SharedPreferences
    private fun guardarDatos(texto: String) {
        val sharedPref = getPreferences(MODE_PRIVATE) // Obtenemos el acceso a SharedPreferences para guardar los datos

        // Obtenemos el conjunto de datos ya guardados, hacemos una copia mutable para poder agregar el nuevo texto
        val datosGuardados = sharedPref.getStringSet("texto_guardado", setOf())?.toMutableSet() ?: mutableSetOf()

        // Añadimos el nuevo texto al conjunto existente
        datosGuardados.add(texto)

        // Guardamos el conjunto actualizado en SharedPreferences
        with(sharedPref.edit()) {
            putStringSet("texto_guardado", datosGuardados.toSet()) // Convertimos a un Set inmutable antes de guardar
            apply() // Guardamos los datos de manera asíncrona
        }
    }

    // Este método se encarga de actualizar la UI con los datos guardados
    private fun actualizarUI() {
        val sharedPref = getPreferences(MODE_PRIVATE) // Obtenemos los datos de SharedPreferences
        val datosGuardados = sharedPref.getStringSet("texto_guardado", setOf()) ?: setOf() // Recuperamos los datos guardados

        // Convertimos el Set en un String con saltos de línea para que se muestre bien en el TextView
        val datosMostrados = datosGuardados.joinToString(separator = "\n")
        binding.outputText.text = datosMostrados // Actualizamos el TextView con los datos guardados
    }
}
