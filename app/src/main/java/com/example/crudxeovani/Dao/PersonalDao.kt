package com.example.crudxeovani.Dao

import androidx.room.*
import androidx.room.Query
import com.example.crudxeovani.models.Personal

@Dao
interface PersonalDao {
    @Query("SELECT * FROM Personal")
    suspend fun getAll():List<Personal>

    @Query("SELECT * FROM Personal WHERE idEmpleado = :id")
    suspend fun getById(id:Long):Personal

    @Insert
    suspend fun insert(personas: List<Personal>):List<Long>

    @Update
    suspend fun upDate(personal: Personal):Int

    @Delete
    suspend fun delete(personal: Personal):Int
}