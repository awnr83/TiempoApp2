package com.moappdev.tiempoapp2.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val url="https://api.openweathermap.org/data/2.5/"

private val moshi= Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit= Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(url)
    .build()

interface TiempoApiService{
    @GET("weather")
    suspend fun getTiempo(
        @Query("q") cityName:String?,
        @Query("appid") apiToken:String?,
        @Query("units") units:String="metric"
    ):Tiempo
}

object TiempoApi{
    val retrofitSeervice: TiempoApiService by lazy{
        retrofit.create(TiempoApiService::class.java)
    }
}