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
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_loisir_activite.*

class Authentification : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentification)
        auth = FirebaseAuth.getInstance();
        val Valider= findViewById<Button>(R.id.Valider)

        Valider.setOnClickListener {

            Toast.makeText(this,"Button is clicked", Toast.LENGTH_LONG).show();

            val intent2 = Intent(this,MainActivity::class.java);

            startActivity(intent2);
            logIn()



        }

    }
    fun logIn(){
        // controle de saisie mail
        val Mail= findViewById<EditText>(R.id.Mail)
        if ( !Patterns.EMAIL_ADDRESS.matcher(Mail.text.toString()).matches()){
            Mail.error = "Veuillez entrer un email valide"
            Mail.requestFocus()
            return
        }
        // controle de saisie mot de passe
        val Password=findViewById<EditText>(R.id.Password)
        if (Password.text.toString().isEmpty()){
            Password.error = "Veuillez remplir le champ Mot de passe"
            Password.requestFocus()
            return
        }
        //methode pour 
        auth.signInWithEmailAndPassword(Mail.text.toString(), Password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user: FirebaseUser? = auth.currentUser
                    updateUI(user)
                } else {
                    updateUI(null)
                }

            }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
    private fun updateUI(currentUser: FirebaseUser?){
        if (currentUser != null){
            startActivity(Intent(this,LoisirActivite::class.java))
        }else{
            Toast.makeText(baseContext, "LogIn failed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}