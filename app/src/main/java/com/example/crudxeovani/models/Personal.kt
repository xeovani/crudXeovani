package com.example.crudxeovani.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Personal(
    @PrimaryKey(autoGenerate = true)
    var idEmpleado:Long,

    var nombre:String,
    var edad:String,
    var calle:String,
    var noExterior:String,
    var noInterior:String,
    var Colonia:String,
    var Entidad:String,
    var alcandiaMunicipio:String,
    var fotografia:String
)
