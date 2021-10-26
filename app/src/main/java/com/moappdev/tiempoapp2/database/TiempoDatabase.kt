package com.moappdev.tiempoapp2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.moappdev.tiempoapp2.database.dao.TiempoDao
import com.moappdev.tiempoapp2.database.entity.TiempoEntity

@Database(entities = [TiempoEntity::class],version = 1)
abstract class TiempoDatabase: RoomDatabase() {

    abstract val tiempoDatabaseDao:TiempoDao

    companion object{
        @Volatile
        private var INSTANCE: TiempoDatabase?=null

        fun getInstance(context: Context): TiempoDatabase? {
            synchronized(this){
                var instance= INSTANCE
                if(instance==null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        TiempoDatabase::class.java,
                        "tiempo"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE=instance
                }
                return instance
            }
        }
    }
}