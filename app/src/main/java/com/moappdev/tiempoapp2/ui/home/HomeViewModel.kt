package com.moappdev.tiempoapp2.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moappdev.tiempoapp2.database.TiempoDatabase
import com.moappdev.tiempoapp2.network.TiempoApi
import com.moappdev.tiempoapp2.repository.RepositoryTiempo
import kotlinx.coroutines.*

class HomeViewModel(private val db:TiempoDatabase): ViewModel() {

    private val repositorio= RepositoryTiempo(db)

    var ciudades= repositorio.allTiempos

    var ciudad= MutableLiveData<String>()
    var pais= MutableLiveData<String>()

    private val _info=MutableLiveData<String>()
    val info:LiveData<String>
        get()=_info

    init {
        _info.value=""
    }

    fun agregar(){
        viewModelScope.launch {
            ciudad.value?.let {
                val tiempo= repositorio.agregarTiempo(it.trim())
                Log.i("alfredo","ultima ${tiempo?.ultima}")
                tiempo?.let {
                    Log.i("alfredo","no null")
//                    _info.value= tiempo.name
                }
            }
        }
    }

    fun actualizar(){
        viewModelScope.launch {
            repositorio.actualizarTodo()
        }
    }

    fun cancelar(){

    }
}