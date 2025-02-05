package com.example.cancha2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class JugadoresActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var jugadoresAdapter: JugadorAdapter
    private val jugadores = mutableListOf(
        Jugador("Lionel Messi", "Inter Miami", R.drawable.jugador01),
        Jugador("Cristiano Ronaldo", "Al-Nassr", R.drawable.jugador02),
        Jugador("Robert Lewandowski", "FC Barcelona", R.drawable.jugador03),
        Jugador("Alexis Sánchez", "Inter de Milán", R.drawable.jugador04),
        Jugador("Kylian Mbappé", "Real Madrid", R.drawable.jugador05)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jugadores)

        // Configurar la Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Habilitar el botón de retroceso
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Configurar el evento onClick para el botón de retroceso
        toolbar.setNavigationOnClickListener {
            onBackPressed() // Regresar a la actividad anterior
        }

        // Referencia al logo
        val logo: ImageView = findViewById(R.id.logo)

        // Configurar el evento onClick para el logo
        logo.setOnClickListener {
            // Redirigir al MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Cierra la actividad actual
        }

        // Configurar el RecyclerView
        recyclerView = findViewById(R.id.recyclerViewJugadores)
        recyclerView.layoutManager = LinearLayoutManager(this)
        jugadoresAdapter = JugadorAdapter(this, jugadores)
        recyclerView.adapter = jugadoresAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == JugadorAdapter.REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = jugadoresAdapter.getLastPhotoUri()
            imageUri?.let {
                jugadoresAdapter.updateJugadorImage(it) // Ahora esta función sí existe
            }
        }
    }
}
