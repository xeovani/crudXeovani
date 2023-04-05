package com.example.crudxeovani

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudxeovani.adaptadores.PersonalAdapter
import com.example.crudxeovani.config.Constantes
import com.example.crudxeovani.databinding.ActivityMainBinding
import com.example.crudxeovani.ui.FormularioActivity
import com.example.crudxeovani.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO ENLAZAR EL MODELO CON LIVEDATA
        viewModel = ViewModelProvider(this).get()
        viewModel.iniciar()
        binding.miRecycler.apply {
            layoutManager = LinearLayoutManager(applicationContext)
        }
        viewModel.personalList.observe(this, Observer{
            binding.miRecycler.adapter = PersonalAdapter(it)
        })

        binding.btnAbrirFormulario.setOnClickListener {
            val intent = Intent(this, FormularioActivity::class.java)
            intent.putExtra(Constantes.OPERACION_KEY, Constantes.OPERACION_INSERTAR)
            startActivity(intent)
        }
    }
}