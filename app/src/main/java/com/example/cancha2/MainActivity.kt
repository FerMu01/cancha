package com.example.cancha2

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Animar la bola de izquierda a derecha
        val animationBall = findViewById<LottieAnimationView>(R.id.animation_ball)
        val screenWidth = resources.displayMetrics.widthPixels.toFloat()

        ObjectAnimator.ofFloat(animationBall, "translationX", 0f, screenWidth - animationBall.width).apply {
            duration = 3500 // Duración en milisegundos
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            start()
        }

        val btnJugadores = findViewById<Button>(R.id.btn_jugadores)
        btnJugadores.setOnClickListener {
            val intent = Intent(this, JugadoresActivity::class.java)
            startActivity(intent)
        }

        val btnGrafico = findViewById<Button>(R.id.btn_grafico)
        btnGrafico.setOnClickListener {
            val intent = Intent(this, GraficoActivity::class.java)
            startActivity(intent)
        }
    }
}
