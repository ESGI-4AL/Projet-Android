package com.example.mitmit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mitmit.recyclerview.LoisirActivite
import com.google.firebase.auth.FirebaseAuth
import java.util.jar.Attributes

class Inscription : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscription)
        auth = FirebaseAuth.getInstance()



        val Valider= findViewById<Button>(R.id.Valider)
        Valider.setOnClickListener {
            Toast.makeText(this,"Button is clicked", Toast.LENGTH_LONG).show();
            InscriptionUser()

        }
    }
    fun InscriptionUser(){
        val Name= findViewById<EditText>(R.id.Name)
        if (Name.text.toString().isEmpty()){
            Name.error = "Veuillez remplir le champ Name"
            Name.requestFocus()
            return
        }
        val Mail= findViewById<EditText>(R.id.Mail)
        if ( !Patterns.EMAIL_ADDRESS.matcher(Mail.text.toString()).matches()){
            Mail.error = "Merci d'entrer un email valide"
            Mail.requestFocus()
            return
        }
        val Password=findViewById<EditText>(R.id.Password)
        if (Password.text.toString().isEmpty()){
            Password.error = "Veuillez remplir le champ Mot de passe"
            Password.requestFocus()
            return
        }
        auth.createUserWithEmailAndPassword(Mail.text.toString(), Password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(baseContext, "Signup failed.",
                        Toast.LENGTH_SHORT).show()
                }

            }
    }

}