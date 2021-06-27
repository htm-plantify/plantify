package com.oddlyspaced.plantify.modal

import com.oddlyspaced.plantify.R

data class Plant(
    val id: Int,
    val name: String,
    val scientificName: String,
    val description: String,
    val weight: Double,
    val height: Double,
    val modelName: String,
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
                    "indoorus_plantus",
                    "very smol",
                    100.0,
                    123.0,
                    "",
                    R.drawable.plant,
                    R.raw.plant_2,
                    100.0,
                ),
            )
        }
    }
}