package com.example.myroomapp

import androidx.room.*
import androidx.room.Room.databaseBuilder

@Database(entities = [Element::class], version = 2, exportSchema = false)
abstract class AppDb : RoomDatabase() {

    abstract val elementDao: ElementDao

    companion object {
        @Volatile
        var instance:AppDb?=null
            get(){
                var instance=field
                return instance?: synchronized(this) { databaseBuilder(
                    MainActivity.context,
                    AppDb::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration().build()
                }.also { field=it }
            }



    }

}