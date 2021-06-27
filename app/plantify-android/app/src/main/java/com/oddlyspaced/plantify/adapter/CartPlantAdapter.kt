package com.oddlyspaced.plantify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oddlyspaced.plantify.databinding.ItemCartPlantBinding
import com.oddlyspaced.plantify.modal.CartItem

class CartPlantAdapter(private val items: ArrayList<CartItem>): RecyclerView.Adapter<CartPlantAdapter.AddPlantViewHolder>() {

    var onItemClick: (CartItem) -> Unit = {}

    class AddPlantViewHolder(private val binding: ItemCartPlantBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CartItem) {
            binding.txCartPlantName.text = data.plant.name
            binding.txCartPlantImage.setImageResource(data.plant.image)
            binding.txCartPlantCost.text = "${data.quantity} x Rs. ${data.plant.price}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddPlantViewHolder {
        return AddPlantViewHolder(ItemCartPlantBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AddPlantViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            onItemClick(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}