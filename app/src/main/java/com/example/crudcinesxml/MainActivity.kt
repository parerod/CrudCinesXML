package com.example.crudcinesxml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.crudcinesxml.modelo.entidades.*
import com.example.crudcinesxml.modelo.servicios.ServiceModelView

class MainActivity : AppCompatActivity() {

    private val svm : ServiceModelView by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        svm.createService()

        svm.assetsToArchivoInterno()
        var cines = svm.getCinesAssetsSimpleXML()
        Log.d("prueba2", "probando procesado con Simple XML Framework")
        cines.forEach {
            Log.d("prueba2", it.toString())
        }


        var actorem : MutableList<Actor> = mutableListOf(
            Actor("Pepe","Principal"),
            Actor("Marita","Secundario"),
            Actor("Dioni","Secundario")
        )
        var actores : Actores = Actores(actorem)
        var peli : Pelicula = Pelicula("1983","Pepe","La vida de pepe","+18","Espanol","69","Spicy","La vida de pepe, la dura vida que tuvo viviendo cerca del Ies Julio Verne",actores)
        var sala : Sala = Sala("20","35",peli)
        var salam : MutableList<Sala> = mutableListOf()
        salam.add(sala)
        var salas = Salas(salam)
        val cinePepe= Cine("Sevilla","Cine de Pepe","C/pepon","656996389",salas)
        svm.addCine(cinePepe)
        cines = svm.getCinesInternoSimpleXML()
        cines.forEach {
            Log.d("pruebaInterno", it.toString())
        }

        var cinesSax = svm.getCinesAssetsSAX()

        cinesSax.forEach {
            Log.d("Saxo",it.toString())
        }


    }
}