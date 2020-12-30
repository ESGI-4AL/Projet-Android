package com.example.mitmit.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mitmit.Activite_Loisirs_Description
import com.example.mitmit.R
import kotlinx.android.synthetic.main.activity_loisir_activite.*
import kotlinx.android.synthetic.main.row.*
import java.text.FieldPosition

class LoisirActivite : AppCompatActivity(), OnLoisirsClickedListener{

    private  val loisirs = listOf(

    Loisirs("Cuisine","food",R.drawable.cuisine),
    Loisirs("Amitié","Fottball...",R.drawable.amitie),
    Loisirs("Informatique","Fottball...",R.drawable.informatique),
    Loisirs("Sport","Fottball...",R.drawable.sport),
    Loisirs("Jeux Video","Fottball...",R.drawable.jeux),
    Loisirs("Voyage","Fottball...",R.drawable.voyage),
    Loisirs("Musique","Fottball...",R.drawable.musique)

    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loisir_activite)


        recyclerView?.apply {

            layoutManager = LinearLayoutManager(this@LoisirActivite)
            adapter = LoisirAdapter(loisirs, this@LoisirActivite)

        }

    }
// à voir cette partie
/////////////////////////////////////////////://////////////////////////// a voir
    /// recupérer l'image et le texte et les afficher
   override fun onLoisirsClicked(loisirs: Loisirs?) {
    Log.d("toto",loisirs.toString())
    val intent = Intent(this, Activite_Loisirs_Description::class.java)
    //intent.putExtra("Titre", item.title)
    //intent.putExtra("Description", item.description)
    //intent.putExtra("Image", item.img.toString())
    startActivity(intent)

    }

}