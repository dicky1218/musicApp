package com.kotlin.musicplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.kotlin.musicplayer.databinding.ActivityRegisterBinding

// handle register account
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding  // binding register activity
    private lateinit var firebaseAuth: FirebaseAuth     // define firebaseauth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)   // Inflate the layout for this fragment
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance() // return firebaseauth instance

        // intent to login activity when click the text view
        binding.textView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // handle checking function
        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString() // set email val to string
            val pass = binding.passET.text.toString()   // set password val to string
            val confirmPass = binding.confirmPassEt.text.toString() // set confirm password val to string

            // email and password checking, when success then intent to login activity
            // otherwise, fail will prompt out warning message
            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields are not Allowed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}