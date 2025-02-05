package com.example.cancha2

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class JugadorAdapter(
    private val context: Activity,
    private val jugadores: MutableList<Jugador>
) : RecyclerView.Adapter<JugadorAdapter.JugadorViewHolder>() {

    private var lastPhotoUri: Uri? = null
    private var lastUpdatedPosition: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JugadorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_jugador, parent, false)
        return JugadorViewHolder(view)
    }

    override fun onBindViewHolder(holder: JugadorViewHolder, position: Int) {
        val jugador = jugadores[position]
        holder.bind(jugador)
        holder.itemView.setOnClickListener {
            if (position == 1) { // Segundo jugador (índice 1)
                val intent = Intent(context, VideoPlayerActivity::class.java)
                intent.putExtra("VIDEO_URI", "android.resource://${context.packageName}/raw/video01")
                context.startActivity(intent)
            }
        }

        // Evento para abrir la cámara
        holder.btnTomarFoto.setOnClickListener {
            val realPosition = holder.adapterPosition
            if (realPosition == RecyclerView.NO_POSITION) return@setOnClickListener

            lastUpdatedPosition = realPosition
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(context.packageManager) != null) {
                val photoFile: File? = try {
                    createImageFile(context)
                } catch (ex: IOException) {
                    null
                }

                photoFile?.also {
                    lastPhotoUri = FileProvider.getUriForFile(
                        context,
                        "${context.packageName}.provider",
                        it
                    )
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, lastPhotoUri)
                    context.startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }


    }

    override fun getItemCount(): Int = jugadores.size

    class JugadorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgJugador: ImageView = itemView.findViewById(R.id.imgJugador)
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombre)
        val txtEquipo: TextView = itemView.findViewById(R.id.txtEquipo)
        val btnTomarFoto: Button = itemView.findViewById(R.id.btnTomarFoto)

        fun bind(jugador: Jugador) {
            txtNombre.text = jugador.nombre
            txtEquipo.text = jugador.equipo
            Glide.with(itemView.context)
                .load(jugador.foto)
                .into(imgJugador)
        }
    }

    private fun createImageFile(context: Context): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
    }

    fun getLastPhotoUri(): Uri? {
        return lastPhotoUri
    }

    fun updateJugadorImage(imageUri: Uri) {
        lastUpdatedPosition?.let { position ->
            jugadores[position] = jugadores[position].copy(foto = imageUri)
            notifyItemChanged(position)
        }
    }

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
    }
}
