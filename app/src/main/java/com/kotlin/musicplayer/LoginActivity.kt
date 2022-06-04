package com.kotlin.musicplayer

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener{

    private var currentUser : FirebaseUser ?= null
    private var firebaseAuth : FirebaseAuth ?= null
    var progressDialog : ProgressDialog?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()
        currentUser = firebaseAuth!!.currentUser
        progressDialog = ProgressDialog(this)

        create_account.setOnClickListener(this)
        login_button.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()

        if (currentUser != null) {
            sendToMainActivity()
        }
    }

    private fun sendToMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
    }

    private fun sendToRegisterActivity() {
        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(p0: View?) {
        if (p0 == create_account) {
            sendToRegisterActivity()
        }
        if (p0 == login_button) {
            allowUserLogin()
        }
    }

    private fun allowUserLogin() {
        val email = login_email.text.toString()
        val password = login_password.text.toString()

        progressDialog!!.setTitle("Loading")
        progressDialog!!.setMessage("Account is creating")
        progressDialog!!.setCanceledOnTouchOutside(true)
        progressDialog!!.show()

        if (email.isEmpty()) {
            Toast.makeText(this@LoginActivity, "Email cannot be empty", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            login_password.error = "cannot be empty"
        } else {
            firebaseAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(object : OnCompleteListener<AuthResult>{
                    override fun onComplete(p0: Task<AuthResult>) {
                        if (p0.isSuccessful) {
                            sendToMainActivity()
                            Toast.makeText(this@LoginActivity, "Login Successfully", Toast.LENGTH_SHORT).show()
                            progressDialog!!.dismiss()
                        } else {
                            Toast.makeText(this@LoginActivity, "Try Again", Toast.LENGTH_SHORT).show()
                            progressDialog!!.dismiss()
                        }
                    }
                })
        }
    }
}