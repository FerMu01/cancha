package com.example.cancha2

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class GraficoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grafico)
        window.statusBarColor = android.graphics.Color.WHITE


        val barChart = findViewById<BarChart>(R.id.barChart)

        // Datos de jugadores y goles
        val jugadores = listOf("Messi", "Ronaldo", "Mbappé", "Haaland", "Neymar")
        val goles = listOf(25, 22, 30, 28, 19)

        // Convertir datos a formato de MPAndroidChart
        val barEntries = goles.mapIndexed { index, goles ->
            BarEntry(index.toFloat(), goles.toFloat())
        }

        val barDataSet = BarDataSet(barEntries, "Goles")
        barDataSet.color = Color.BLUE
        barDataSet.valueTextSize = 16f

        val barData = BarData(barDataSet)
        barChart.data = barData

        // Configurar el eje X con los nombres de los jugadores
        val xAxis = barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(jugadores)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(true)
        xAxis.granularity = 1f
        xAxis.textSize = 12f

        // Configuración general
        barChart.description.isEnabled = false
        barChart.animateY(1500)
        barChart.invalidate()
    }
}
