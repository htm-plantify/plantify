package com.oddlyspaced.plantify.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.oddlyspaced.plantify.databinding.FragmentPlantInfoBinding
import com.oddlyspaced.plantify.modal.Plant

class PlantInfoFragment: BottomSheetDialogFragment() {

    private lateinit var binding: FragmentPlantInfoBinding
    var data: Plant? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        FragmentPlantInfoBinding.inflate(inflater).let {
            binding = it
            return it.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        data?.let {
            binding.imgInfoPlant.setImageResource(it.image)
            binding.txInfoTitle.text = it.name
            binding.txInfoCost.text = "Rs. ${it.price}"
            binding.txInfoHt.text = "${it.height} cm"
            binding.txInfoWt.text = "${it.weight} gm"
            binding.txInfoDesc.text = it.description
        }
    }
}