package com.moappdev.tiempoapp2.network

import com.moappdev.tiempoapp2.database.entity.TiempoEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter=true)
data class Tiempo(
    val id: Long?=0,
    val name:String?="",
    val visibility:Long=0,
    val cod:Int?=null,
    val main:Main?=null,
    val wind:Wind?=null,
    val sys:Sys?=null
)

@Json(name="main")
data class Main(
    val temp:Double?=null,
    val feel_like:Double?=null,
    val temp_min:Double?=null,
    val temp_max:Double?=null,
    val presurre:Int?=null,
    val humidity:Int?=null,
    val sea_level:Int?=null,
    val grnd_level:Int?=null
)

@Json(name="wind")
data class Wind(
    val speed:Double?=null,
    val deg:Int?=null,
    val gust:Double?=null
)

@Json(name="sys")
data class Sys(
    val type:Int?=null,
    val id:Long?=null,
    val country:String?=null,
    val sunrise:Double?=null,
    val sunset:Double?=null
)

fun Tiempo.asDatabaseModel(): TiempoEntity{
    return TiempoEntity(
        idTiempo = this.id,
        name= this.name,
        visibility= this.visibility,
        cod= this.cod,
        main= Main(
            temp = main!!.temp,
            temp_min = main!!.temp_min,
            temp_max = main!!.temp_max,
            presurre = main!!.presurre,
            humidity = main!!.humidity,
            sea_level = main!!.sea_level,
            grnd_level = main!!.grnd_level
        ),
        wind= Wind(
            speed = wind!!.speed,
            deg = wind!!.deg,
            gust = wind!!.gust
        ),
        sys= Sys(
            id = sys!!.id,
            country = sys!!.country,
            sunrise = sys!!.sunrise,
            sunset = sys!!.sunset
        )
    )
}