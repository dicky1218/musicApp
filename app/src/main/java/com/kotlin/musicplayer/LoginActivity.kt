package com.kotlin.musicplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    var currentUser : FirebaseUser ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onStart() {
        super.onStart()

        if (currentUser != null) {
            sendToMainActivity()
        }
    }

    private fun sendToMainActivity() {
        var intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
    }
}