package com.oddlyspaced.arcore_test

import android.app.ActivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import java.util.*

class MainActivity : AppCompatActivity() {

    private companion object {
        const val TAG = "MainActivity"
    }

    private val arCam by lazy {
        supportFragmentManager.findFragmentById(R.id.arCameraArea) as ArFragment
    }

    private var clickNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!checkSystemSupport()) {
            Log.d(TAG, "Device not supported!")
            finish()
        }

        setupArCam()
    }

    private fun checkSystemSupport() = (getSystemService(ACTIVITY_SERVICE) as ActivityManager).deviceConfigurationInfo.glEsVersion.toDouble() >= 3.0

    private fun addModel(anchor: Anchor, modelRenderable: ModelRenderable) {
        val anchorNode = AnchorNode(anchor)
        anchorNode.setParent(arCam.arSceneView.scene)
        TransformableNode(arCam.transformationSystem).apply {
            setParent(anchorNode)
            renderable = modelRenderable
            scaleController.maxScale = 1F
            scaleController.minScale = 0.001F
            select()
        }
    }

    private fun setupArCam() {
        arCam.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            clickNumber++
            if (clickNumber == 1) {
                val anchor = hitResult.createAnchor()
                ModelRenderable.builder().apply {
                    setSource(applicationContext, R.raw.ar_model)
                    setIsFilamentGltf(true)
                    build().apply {
                        thenAccept { modelRenderable ->
                            addModel(anchor, modelRenderable)
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