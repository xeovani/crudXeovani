package com.example.crudxeovani.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudxeovani.config.Constantes
import com.example.crudxeovani.config.PersonalApp.Companion.db
import com.example.crudxeovani.models.Personal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FormularioViewModel:ViewModel() {
    var id = MutableLiveData<Long>()
    var nombre = MutableLiveData<String>()
    var edad = MutableLiveData<String>()
    var calle = MutableLiveData<String>()
    var noExterior = MutableLiveData<String>()
    var noInterior = MutableLiveData<String>()
    var colonia = MutableLiveData<String>()
    var entidad = MutableLiveData<String>()
    var alcaldiaMpio = MutableLiveData<String>()
    var operacion = Constantes.OPERACION_INSERTAR
    var fotografia = MutableLiveData<String>()
    var operacionExitosa = MutableLiveData<Boolean>()
    init {
        fotografia.value = ""
    }

    fun guardarUsuario(){
        if (validarInformacion()){
            var mPersonal = Personal(0,
                nombre.value!!,
                edad.value!!,
                calle.value!!,
                noExterior.value!!,
                noInterior.value!!,
                colonia.value!!,
                entidad.value!!,
                alcaldiaMpio.value!!,
                fotografia.value!!
            )
            when(operacion){
                Constantes.OPERACION_INSERTAR->{
                    viewModelScope.launch {
                        val result = withContext(Dispatchers.IO){
                            db.personalDao().insert(
                                arrayListOf<Personal>(mPersonal)
                            )
                        }
                        operacionExitosa.value = result.isNotEmpty()
                    }
                }
                Constantes.OPERACION_EDITAR->{
                    mPersonal.idEmpleado = id.value!!
                    viewModelScope.launch {
                        var result = withContext(Dispatchers.IO){
                            db.personalDao().upDate(mPersonal)
                        }
                        operacionExitosa.value = (result>0)
                    }
                }
            }
        }
        else{
            operacionExitosa.value = false
        }


    }

    fun cargarDatos() {
        viewModelScope.launch {
            var persona = withContext(Dispatchers.IO){
              db.personalDao().getById(id.value!!)
            }
            nombre.value = persona.nombre
            edad.value = persona.edad
            calle.value = persona.calle
            noExterior.value = persona.noExterior
            noInterior.value = persona.noInterior
            colonia.value = persona.Colonia
            entidad.value = persona.Entidad
            alcaldiaMpio.value = persona.alcandiaMunicipio
            fotografia.value = persona.fotografia
        }
    }

    private fun validarInformacion():Boolean{

        //Devuelve true si la informacion no es nula o vacio
        return !(nombre.value.isNullOrEmpty() ||
                edad.value.isNullOrEmpty() ||
                calle.value.isNullOrEmpty() ||
                noExterior.value.isNullOrEmpty() ||
                noInterior.value.isNullOrEmpty() ||
                colonia.value.isNullOrEmpty() ||
                entidad.value.isNullOrEmpty() ||
                alcaldiaMpio.value.isNullOrEmpty() ||
                fotografia.value.isNullOrEmpty()

                )
    }
}