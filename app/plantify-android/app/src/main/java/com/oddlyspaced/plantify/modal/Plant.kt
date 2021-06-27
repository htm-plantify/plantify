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
                    "Aglaonema Indoor Plant",
                    "Aglaonema",
                    "Beautiful small potted plant, which can be kept anywhere with decent sunlight and humidity. Great addition to bookshelves. Needs infrequent watering",
                    234.0,
                    19.0,
                    "",
                    R.drawable.indoor,
                    R.raw.indoor,
                    115.0,
                ),
                Plant(
                    1,
                    "Areca Palm Plant",
                    "Dypsis lutescens\n",
                    "Once an endangered species, the Areca Palm is a staple in Indian spaces. One of the most undervalued plants in the indoor plant category. This excellent air purifier is pet friendly and easy to grow. Its delicate leaves/fronds grow outwards in a curved manner and add a tropical feel to any space and can adapt to a variety of growing conditions.",
                    1234.0,
                    47.0,
                    "",
                    R.drawable.outdoor,
                    R.raw.outdoor,
                    545.0,
                ),
            )
        }
    }
}