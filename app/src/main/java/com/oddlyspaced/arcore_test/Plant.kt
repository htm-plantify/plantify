package com.oddlyspaced.arcore_test

data class Plant(
    val id: Int,
    val name: String,
    val image: Int,
    val model: Int,
) {
    companion object {
        fun getPlantList(): ArrayList<Plant> {
            return arrayListOf(
                Plant(
                    0,
                    "Indoor Plant",
                    R.drawable.plant,
                    R.raw.plant_2
                ),
                Plant(
                    0,
                    "Outdoor Plant",
                    R.drawable.plant,
                    R.raw.plant_1
                )
            )
        }
    }
}