package com.example.crudcinesxml.modelo.servicios

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.crudcinesxml.modelo.dao.DaoXML
import com.example.crudcinesxml.modelo.entidades.Cine

private lateinit var cines : MutableList<Cine>

private lateinit var daoXML : DaoXML

class ServiceModelView(application: Application) : AndroidViewModel(application)  {

    fun createService() {
        cines  = mutableListOf()

        daoXML = DaoXML(getApplication())
    }

    fun getCinesAssetsSAX(): MutableList<Cine> {
        var cines = daoXML.procesarArchivoXMLSAX()
        return cines
    }

    fun getCinesAssetsSimpleXML() : MutableList<Cine> {
        var cines = daoXML.procesarArchivoAssetsXML()
        return cines
    }

    fun getCinesInternoSimpleXML():MutableList<Cine>{
        var cines = daoXML.procesarArchivoXMLInterno()
        return cines
    }

    fun addCine(cin : Cine){
        daoXML.addCine(cin)
    }

    fun assetsToArchivoInterno(){
        daoXML.copiarArchivoDesdeAssets()
    }



}