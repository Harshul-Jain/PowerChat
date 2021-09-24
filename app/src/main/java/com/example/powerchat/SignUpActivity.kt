package com.example.powerchat

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
        userImgView.setImageURI(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        userImgView.setOnClickListener {
            checkPermissionForImage()
            // Firebase Extensions - Image Thumbnail

        }
    }

    private fun checkPermissionForImage() {
        if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
            && (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
        ) {
            val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            val permissionWrite = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

            requestPermissions(
                permission,
                1001
            )

            requestPermissions(
                permissionWrite,
                1002
            )
        } else {
            pickImageFromGallery()
        }
    }

    private fun pickImageFromGallery() {
        getContent.launch("image/*")
    }


}