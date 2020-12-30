package com.example.mitmit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.mitmit.recyclerview.LoisirActivite


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Identifier= findViewById<Button>(R.id.Identifier)
        val Connecter= findViewById<Button>(R.id.Connecter)

        Identifier.setOnClickListener {

              Toast.makeText(this,"Button is clicked", Toast.LENGTH_LONG).show();

              val intent = Intent(this,Authentification::class.java);

              startActivity(intent);

        }

        Connecter.setOnClickListener {

            Toast.makeText(this,"Button is clicked", Toast.LENGTH_LONG).show();

            val intent2 = Intent(this,LoisirActivite::class.java);

            startActivity(intent2);

        }


    }
}