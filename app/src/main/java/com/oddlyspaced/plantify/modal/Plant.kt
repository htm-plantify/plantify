package com.oddlyspaced.plantify.modal

import com.oddlyspaced.plantify.R

data class Plant(
    val id: Int,
    val name: String,
    val image: Int,
    val model: Int,
    val price: Double,
) {
    companion object {
        fun getPlantList(): ArrayList<Plant> {
            return arrayListOf(
                Plant(
                    0,
                    "Indoor Plant",
                    R.drawable.plant,
                    R.raw.plant_2,
                    100.0,
                ),
                Plant(
                    0,
                    "Outdoor Plant",
                    R.drawable.plant,
                    R.raw.plant_1,
                    250.0,
                )
            )
        }
    }
}