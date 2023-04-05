package com.example.crudxeovani.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.crudxeovani.MainActivity
import com.example.crudxeovani.R
import com.example.crudxeovani.config.Constantes
import com.example.crudxeovani.databinding.ActivityFormularioBinding
import com.example.crudxeovani.viewmodel.FormularioViewModel

class FormularioActivity : AppCompatActivity() {

    lateinit var binding: ActivityFormularioBinding
    lateinit var viewModel: FormularioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get()
        viewModel.operacion = intent.getStringExtra(Constantes.OPERACION_KEY)!!

        binding.modelo = viewModel
        binding.lifecycleOwner = this

        viewModel.operacionExitosa.observe(this, Observer {
            if (it){
                mostrarMensaje("Operacion Exitosa")
                irAlInicio()
            }else{
                mostrarMensaje("Ocurrio un error")
            }
        })
        if (viewModel.operacion.equals(Constantes.OPERACION_EDITAR)){
            viewModel.id.value = intent.getLongExtra(Constantes.ID_PERSONAL_KEY,0)
            viewModel.cargarDatos()
            binding.linearEditar.visibility = View.VISIBLE
            binding.btnGuardar.visibility = View.GONE
        }else{
            binding.linearEditar.visibility = View.GONE
            binding.btnGuardar.visibility = View.VISIBLE
        }
    }

    private fun irAlInicio() {
        var intent = Intent(applicationContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun mostrarMensaje(s: String) {
        Toast.makeText(applicationContext,s,Toast.LENGTH_LONG).show()
    }
}