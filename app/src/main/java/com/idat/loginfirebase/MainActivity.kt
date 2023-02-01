package com.idat.loginfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.idat.loginfirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var b: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)


        b.btnSignOut.setOnClickListener {
            signOut()
        }

        val email = intent.getStringExtra("email")
        val displayName = intent.getStringExtra("displayName")
        b.txtCorreo.text = email
        b.txtNombre.text = displayName
    }


    private fun signOut() {

        googleClient = GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN)
        googleClient.signOut()
            .addOnCompleteListener(this) {
                // Actualice la interfaz de usuario aquí
                updateUI()
            }

    }

    private fun showInfoUser() {

        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            // Usuario conectado
            val email = user.email
            val displayName = user.displayName
            b.txtCorreo.text = email
            b.txtNombre.text = displayName
        } else {
            // Usuario no conectado
            updateUI()
        }
    }

    private fun updateUI() {
        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)
    }
}