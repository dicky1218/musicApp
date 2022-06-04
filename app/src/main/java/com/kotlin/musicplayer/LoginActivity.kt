package com.kotlin.musicplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener{

    var currentUser : FirebaseUser ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        create_account.setOnClickListener(this)
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

    private fun sendToRegisterActivity() {
        var intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(p0: View?) {
        if (p0 == create_account) {
            sendToRegisterActivity()
        }
    }
}