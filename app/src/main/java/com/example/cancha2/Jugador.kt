package com.example.cancha2

data class Jugador(
    val nombre: String,
    val equipo: String,
    var foto: Any // Permite tanto R.drawable.xxx como Uri
)