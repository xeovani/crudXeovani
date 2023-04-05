package com.example.crudxeovani.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.crudxeovani.Dao.PersonalDao
import com.example.crudxeovani.models.Personal

@Database(
    entities = [Personal::class],
    version = 2
)
abstract class PersonalDb: RoomDatabase(){
    abstract fun personalDao():PersonalDao
}