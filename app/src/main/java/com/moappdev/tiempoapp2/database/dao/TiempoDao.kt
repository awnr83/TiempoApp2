package com.moappdev.tiempoapp2.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.moappdev.tiempoapp2.database.entity.TiempoEntity
import org.jetbrains.annotations.Nullable

@Dao
interface TiempoDao {

    @Insert
    fun insertTiempo(tiempo: TiempoEntity)

    @Update
    fun actualizarTiempo(tiempo: TiempoEntity)

    @Query("select * from tiempo where name= :nombreCiudad")
    fun existe(nombreCiudad: String):TiempoEntity?

    @Query("select * from tiempo")
    fun allTiempo():LiveData<List<TiempoEntity>>
}