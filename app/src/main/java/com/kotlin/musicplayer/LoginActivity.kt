package com.kotlin.musicplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.kotlin.musicplayer.databinding.ActivityLoginBinding
import java.util.concurrent.Executor

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding  // binding the login activity
    private lateinit var firebaseAuth: FirebaseAuth     // define firebase auth

    private lateinit var executor: Executor     // for creating a thread and run a long-running task
    private lateinit var biometricPrompt: BiometricPrompt   // manage a system-provided biometric prompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo // configure how the BiometricPrompt should appear and behave


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater) // Inflate the layout for this fragment
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()   // return firebaseauth instance

        // intent the text switch to register activity
        binding.textView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // handle checking function
        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()     // set email val to string
            val pass = binding.passET.text.toString()   // set password val to string

            login(email, pass)
        }

        executor = ContextCompat.getMainExecutor(this)

        // provided to BiometricPrompt after calling the BiometricPrompt's authenticate function
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(applicationContext,
                        "Authentication error: $errString", Toast.LENGTH_SHORT)
                        .show()
                }

                // when auth success, intent the view to main activity
                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(applicationContext,
                        "Authentication succeeded!", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })

        // provide appearance and behavioural configuration the the BiometricPrompt object
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()

        // prompt the system's Biometric Prompt
        val biometricLoginButton = findViewById<Button>(R.id.biometric_login)
        biometricLoginButton.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }

    }

    // used to check the availability of the system's biometric feature
    // check for the presence of a PIN, pattern, or password
    override fun onResume() {
        super.onResume()
        val biometricStatusTextView = findViewById<TextView>(R.id.biometric_status)
        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS ->
                biometricStatusTextView.text = "App can authenticate using biometrics."
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                biometricStatusTextView.text = "No biometric features available on this device."
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                biometricStatusTextView.text = "Biometric features are currently unavailable."
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                // Prompts the user to create credentials that your app accepts.
                biometricStatusTextView.text = "Biometric features are not enrolled."
        }
    }

    fun login(email: String, pass: String) {
        // email and password checking, and intent view to main activity
        if (email.isNotEmpty() && pass.isNotEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "Empty Fields are not Allowed", Toast.LENGTH_SHORT).show()
        }
    }
}