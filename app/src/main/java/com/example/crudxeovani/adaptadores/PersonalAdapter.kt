package com.example.crudxeovani.adaptadores

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crudxeovani.R
import com.example.crudxeovani.config.Constantes
import com.example.crudxeovani.databinding.ItemListBinding
import com.example.crudxeovani.models.Personal
import com.example.crudxeovani.ui.FormularioActivity


class PersonalAdapter(private val dataSet: List<Personal>?) :
    RecyclerView.Adapter<PersonalAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet?.get(position)
        viewHolder.enlazarItem(item!!)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet!!.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = ItemListBinding.bind(view)
        var contexto = view.context
        fun enlazarItem(personal: Personal) {

            binding.tvNombre.text = personal.nombre
            binding.tvEdad.text = personal.edad
            binding.tvCalle.text = personal.calle
            binding.tvNoExterior.text = personal.noExterior
            binding.tvNoInterior.text = personal.noInterior
            binding.tvEntidad.text = personal.Entidad
            binding.tvAlcMpio.text = personal.alcandiaMunicipio

            binding.root.setOnClickListener {
                var intent = Intent(contexto,FormularioActivity::class.java)
                intent.putExtra(Constantes.OPERACION_KEY,Constantes.OPERACION_EDITAR)
                intent.putExtra(Constantes.ID_PERSONAL_KEY,personal.idEmpleado)
                contexto.startActivity(intent)
            }
        }

    }
}
