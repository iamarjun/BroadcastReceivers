package com.arjun.broadcastreceivers

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val receiver by lazy { MyBroadcastReceiver() }
    private val intentFilter by lazy { IntentFilter("foo.bar") }
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {

        if (it) {
            // Permission is granted. Continue the action or workflow in your
            // app.
        } else {
            // Explain to the user that the feature is unavailable because the
            // features requires a permission that the user has denied. At the
            // same time, respect the user's decision. Don't link to system
            // settings in an effort to convince the user to change their
            // decision.
            finish()
        }
    }

    private val myIntent by lazy { Intent() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED) {

//        } else if () {
//            // In an educational UI, explain to the user why your app requires this
//            // permission for a specific feature to behave as expected. In this UI,
//            // include a "cancel" or "no thanks" button that allows the user to
//            // continue using your app without granting the permission.
//            showInContextUI(...);
//        }

            myIntent.apply {
                action = "foo.bar"
                addCategory(Intent.CATEGORY_DEFAULT)
            }


        } else {
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            requestPermissionLauncher.launch(Manifest.permission.RECEIVE_SMS)
        }

        register.setOnClickListener {
            sendBroadcast(myIntent)
        }
    }

    override fun onStart() {
        super.onStart()
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT)
        registerReceiver(receiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}