package com.example.crudxeovani.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudxeovani.config.PersonalApp.Companion.db
import com.example.crudxeovani.models.Personal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {
    val personalList = MutableLiveData<List<Personal>>()
    var parametroBusqueda = MutableLiveData<String>()

    fun iniciar(){
        viewModelScope.launch {
            personalList.value = withContext(Dispatchers.IO){
                db.personalDao().insert(arrayListOf<Personal>(
                    Personal(0,
                        "Jose",
                        "19",
                        "Tulipan",
                        "4",
                        "3",
                        "Arbolada",
                    "cd de Mexico",
                        "Benito Juarez",
                        ""
                    ),
                    Personal(0,
                        "Daniel",
                        "22",
                        "Cedros",
                        "5",
                        "2",
                        "Arbolada",
                        "cd de Mexico",
                        "Benito Juarez",
                        ""
                    )
                ))

                db.personalDao().getAll()
            }

        }
    }
}