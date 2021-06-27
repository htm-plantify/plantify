package com.oddlyspaced.plantify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oddlyspaced.plantify.databinding.ItemAddPlantBinding
import com.oddlyspaced.plantify.modal.Plant

class AddPlantAdapter(private val plants: ArrayList<Plant>): RecyclerView.Adapter<AddPlantAdapter.AddPlantViewHolder>() {

    var onItemClick: (Plant) -> Unit = {}

    class AddPlantViewHolder(private val binding: ItemAddPlantBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Plant) {
            binding.txAddPlantName.text = data.name
            binding.txAddPlantImage.setImageResource(data.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddPlantViewHolder {
        return AddPlantViewHolder(ItemAddPlantBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AddPlantViewHolder, position: Int) {
        holder.bind(plants[position])
        holder.itemView.setOnClickListener {
            onItemClick(plants[position])
        }
    }

    override fun getItemCount(): Int {
        return plants.size
    }
}