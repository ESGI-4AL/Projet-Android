package com.example.mitmit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.mitmit.recyclerview.LoisirActivite
import kotlinx.android.synthetic.main.activity_loisir_activite.*

class Authentification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentification)

        val Valider= findViewById<Button>(R.id.Valider)

        Valider.setOnClickListener {

            Toast.makeText(this,"Button is clicked", Toast.LENGTH_LONG).show();

            val intent2 = Intent(this,MainActivity::class.java);

            startActivity(intent2);



        }

    }
}