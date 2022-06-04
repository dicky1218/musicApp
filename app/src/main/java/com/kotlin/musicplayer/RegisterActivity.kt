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
import kotlinx.android.synthetic.main.activity_register.*

// handle register account
class RegisterActivity : AppCompatActivity() , View.OnClickListener{

    private var mAuth : FirebaseAuth ?= null
    var progressDialog : ProgressDialog ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)

        already_have_account.setOnClickListener(this)

        register_button.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (p0 == already_have_account) {
            sendToLoginActivity()
        }
        if (p0 == register_button) {
            createNewAccount()
        }
    }

    private fun createNewAccount() {
        val email = register_email.text.toString()
        val password = register_password.text.toString()

        progressDialog!!.setTitle("Loading")
        progressDialog!!.setMessage("Account is creating")
        progressDialog!!.setCanceledOnTouchOutside(true)
        progressDialog!!.show()

        // checking email and password
        if (email.isEmpty()) {
            Toast.makeText(this@RegisterActivity, "Email cannot be empty", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            register_password.error = "cannot be empty"
        } else {
            mAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(object : OnCompleteListener<AuthResult>{
                    override fun onComplete(p0: Task<AuthResult>) {
                        if (p0.isSuccessful) {
                            sendToLoginActivity()
                            Toast.makeText(this@RegisterActivity, "Account Created Successfully",
                            Toast.LENGTH_SHORT).show()
                            progressDialog!!.dismiss()
                        } else {
                            Toast.makeText(this@RegisterActivity, "Try Again", Toast.LENGTH_SHORT).show()
                            progressDialog!!.dismiss()
                        }
                    }

                })
        }
    }

    private fun sendToLoginActivity() {
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        startActivity(intent)
    }
}