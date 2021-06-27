package com.oddlyspaced.arcore_test

import android.app.ActivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.oddlyspaced.arcore_test.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private companion object {
        const val TAG = "MainActivity"
    }

    private val arCam by lazy {
        supportFragmentManager.findFragmentById(R.id.arCameraArea) as ArFragment
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var anchor: Anchor
    private lateinit var anchorNode: AnchorNode

    private var plantToAdd: Plant? = null
    private val cartMap = mutableMapOf<Plant, ArrayList<PlantExisting>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).let {
            binding = it
            setContentView(it.root)
        }

        if (!checkSystemSupport()) {
            Log.d(TAG, "Device not supported!")
            finish()
        }

        setupViews()
        setupArCam()
    }

    private fun checkSystemSupport() = (getSystemService(ACTIVITY_SERVICE) as ActivityManager).deviceConfigurationInfo.glEsVersion.toDouble() >= 3.0

    private fun setupViews() {
        binding.rvAddPlant.apply {
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            adapter = AddPlantAdapter(Plant.getPlantList()).apply {
                onItemClick = {
                    plantToAdd = it
                    binding.cvAddPlantContainer.isVisible = false
                }
            }
        }

        binding.cvAddPlant.setOnClickListener {
            binding.cvAddPlantContainer.isVisible = !binding.cvAddPlantContainer.isVisible
        }

        binding.cvCamera.setOnClickListener {
            cartMap.forEach { (_, u) ->
                u.forEach {
                    removeModel(it)
                }
            }
        }
    }

    private fun addModel(modelRenderable: ModelRenderable): TransformableNode {
        return TransformableNode(arCam.transformationSystem).apply {
            setParent(anchorNode)
            renderable = modelRenderable
            scaleController.maxScale = 1F
            scaleController.minScale = 0.001F
            select()
        }
    }

    private fun removeModel(plantExisting: PlantExisting) {
        plantExisting.anchorNode.removeChild(plantExisting.node)
    }

    private fun setupArCam() {
        arCam.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            anchor = hitResult.createAnchor()
            anchorNode = AnchorNode(anchor)
            anchorNode.setParent(arCam.arSceneView.scene)
            plantToAdd?.let { plant ->
                ModelRenderable.builder().apply {
                    setSource(applicationContext, plant.model)
                    setIsFilamentGltf(true)
                    build().apply {
                        thenAccept { modelRenderable ->
                            cartMap[plantToAdd]?.add(
                                PlantExisting(
                                    anchorNode,
                                    addModel(modelRenderable)
                                )
                            ) ?: run {
                                cartMap[plantToAdd!!] = arrayListOf(
                                    PlantExisting(
                                        anchorNode,
                                        addModel(modelRenderable)
                                    )
                                )
                            }
                            plantToAdd = null
                        }
                        exceptionally {
                            Log.e(TAG, "Some error occured!")
                            it.printStackTrace()
                            null
                        }
                    }
                }
            }
        }
    }

}

data class PlantExisting(
    val anchorNode: AnchorNode,
    val node: TransformableNode
)