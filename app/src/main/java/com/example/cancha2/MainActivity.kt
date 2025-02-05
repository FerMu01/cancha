package com.example.cancha2

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button // Importa la clase Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ajustar padding para evitar que el contenido se superponga con la barra de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Botón Jugadores Destacados
        val btnJugadores = findViewById<Button>(R.id.btn_jugadores)
        btnJugadores.setOnClickListener {
            val intent = Intent(this, JugadoresActivity::class.java)
            startActivity(intent)
        }

        // Botón Gráfico de Goleadores
        val btnGrafico = findViewById<Button>(R.id.btn_grafico)
        btnGrafico.setOnClickListener {
            val intent = Intent(this, GraficoActivity::class.java)
            startActivity(intent)
        }
    }
}
