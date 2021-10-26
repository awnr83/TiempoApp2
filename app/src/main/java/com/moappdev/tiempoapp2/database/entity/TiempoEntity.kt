package com.moappdev.tiempoapp2.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moappdev.tiempoapp2.network.Main
import com.moappdev.tiempoapp2.network.Sys
import com.moappdev.tiempoapp2.network.Wind
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "tiempo")
data class TiempoEntity(
    @PrimaryKey
    val idTiempo: Long?=0,
    val name:String?="",
    val visibility:Long=0,
    val cod:Int?=null,
    var ultima:String?=null,
    @Embedded val main: Main?=null,
    @Embedded val wind:Wind?=null,
    @Embedded val sys: Sys?=null
){
    fun setUltimaActualizacion(){
        val sdf=SimpleDateFormat("dd/M/yy HH:mm")
        val currentDate= sdf.format(Date())
        this.ultima= currentDate.toString()
    }
}

data class Main(
    val temp:Double?,
    val feel_like:Double?=null,
    val temp_min:Double?=null,
    val temp_max:Double?=null,
    val presurre:Int?=null,
    val humidity:Int?=null,
    val sea_level:Int?=null,
    val grnd_level:Int?=null
)

data class Wind(
    val speed:Double?=null,
    val deg:Int?=null,
    val gust:Double?=null
)

data class Sys(
    val id:Long?=null,
    val country:String?=null,
    val sunrise:Double?=null,
    val sunset:Double?=null
)