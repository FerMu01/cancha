package com.example.cancha2

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
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
        window.statusBarColor = Color.WHITE
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val logo: ImageView = findViewById(R.id.logo)
        logo.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val barChart = findViewById<BarChart>(R.id.barChart)

        // Datos de jugadores y goles
        val jugadores = listOf("Messi", "Ronaldo", "Lewandowski", "Alexis", "Mbappe")
        val goles = listOf(25, 22, 30, 28, 19)

        // Convertir datos a formato de MPAndroidChart
        val barEntries = goles.mapIndexed { index, goles ->
            BarEntry(index.toFloat(), goles.toFloat())
        }

        val colores = listOf(Color.MAGENTA, Color.YELLOW, Color.BLUE, Color.GREEN, Color.RED)

        val barDataSet = BarDataSet(barEntries, "")
        barDataSet.setColors(colores)
        barDataSet.valueTextSize = 22f

        val typeface = ResourcesCompat.getFont(this, R.font.oswaldsemibold)
        if (typeface != null) {
            barDataSet.valueTypeface = typeface
        }

        val barData = BarData(barDataSet)
        barChart.data = barData
        barChart.legend.isEnabled = false

        // Configurar el eje X con los nombres de los jugadores
        val xAxis = barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(jugadores)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(true)
        xAxis.granularity = 1f
        xAxis.textSize = 12f
        xAxis.labelRotationAngle = 45f  // Rotar el texto para evitar que se recorte

        // Agregar margen extra en la parte inferior del gráfico
        barChart.setExtraOffsets(0f, 0f, 0f, 20f)

        // Aplicar la misma fuente al eje X
        if (typeface != null) {
            xAxis.typeface = typeface
        }

        // Configuración general
        barChart.description.isEnabled = false
        barChart.animateY(1500)
        barChart.invalidate()
    }
}
