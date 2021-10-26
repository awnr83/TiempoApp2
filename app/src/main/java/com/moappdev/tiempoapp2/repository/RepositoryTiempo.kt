package com.moappdev.tiempoapp2.repository

import android.util.Log
import com.moappdev.tiempoapp2.database.TiempoDatabase
import com.moappdev.tiempoapp2.database.entity.TiempoEntity
import com.moappdev.tiempoapp2.network.Tiempo
import com.moappdev.tiempoapp2.network.TiempoApi
import com.moappdev.tiempoapp2.network.asDatabaseModel
import com.moappdev.tiempoapp2.util.KEY_API
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryTiempo(private val db:TiempoDatabase) {


    val allTiempos= db.tiempoDatabaseDao.allTiempo()

    suspend fun agregarTiempo(ciudad: String):TiempoEntity? {
        return withContext(Dispatchers.IO){
            try{
                val tiempo= db.tiempoDatabaseDao.existe(ciudad)
                val tiempoNetwork= TiempoApi.retrofitSeervice.getTiempo(ciudad, KEY_API)

                if(tiempo?.idTiempo == tiempoNetwork.id){
                    val tiempoE=tiempoNetwork.asDatabaseModel()
                    tiempoE.setUltimaActualizacion()
                    db.tiempoDatabaseDao.actualizarTiempo(tiempoE)
                    tiempoE
                }else {
                    val tiempoE=tiempoNetwork.asDatabaseModel()
                    tiempoE.setUltimaActualizacion()
                    db.tiempoDatabaseDao.insertTiempo(tiempoE)
                    tiempoE
                }
            }catch (e:Exception){
                Log.i("alfredo", "error: ${e.message} ")
                null
            }
        }
    }

    suspend fun actualizarTiempo(ciudad:String){
        withContext(Dispatchers.IO) {
            try {
                var temp = TiempoApi.retrofitSeervice.getTiempo(ciudad, KEY_API)
                var tiempo= temp.asDatabaseModel()
            }catch (e:Exception){
                Log.i("alfredo", "error: ${e.message} ")
            }
        }
    }
    suspend fun actualizarTodo(){
        withContext(Dispatchers.IO){
            try {
                allTiempos.value?.forEach {
                    var temp= TiempoApi.retrofitSeervice.getTiempo(it.name, KEY_API)
                    var tiempo= temp.asDatabaseModel()
                    tiempo.setUltimaActualizacion()
                    db.tiempoDatabaseDao.actualizarTiempo(tiempo)
                }

            }catch (e:Exception){
                Log.i("alfredo", "error: ${e.message} ")
            }
        }
    }
}