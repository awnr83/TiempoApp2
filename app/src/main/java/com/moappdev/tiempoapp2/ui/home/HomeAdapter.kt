package com.moappdev.tiempoapp2.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moappdev.tiempoapp2.database.entity.TiempoEntity
import com.moappdev.tiempoapp2.databinding.ItemCiudadBinding

class HomeAdapter():ListAdapter<TiempoEntity, HomeAdapter.ViewHolder>(TiempoDiff()) {

    class ViewHolder(val binding: ItemCiudadBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(item:TiempoEntity){
            binding.apply {
                tvCiudad.text= item.name
                tvPais.text=item.sys?.country
                tvTempMin.text="Temp min: ${item.main?.temp_min.toString()}"
                tvTempMax.text="Temp max: ${item.main?.temp_max.toString()}"
            }
        }
        companion object{
            fun from(parent: ViewGroup):ViewHolder{
                val layoutInflater= LayoutInflater.from(parent.context)
                val binding= ItemCiudadBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }

    class TiempoDiff:DiffUtil.ItemCallback<TiempoEntity>() {
        override fun areItemsTheSame(oldItem: TiempoEntity, newItem: TiempoEntity): Boolean {
            return oldItem===newItem
        }
        override fun areContentsTheSame(oldItem: TiempoEntity, newItem: TiempoEntity): Boolean {
            return oldItem.idTiempo==newItem.idTiempo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}