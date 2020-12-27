package com.example.mitmit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast

class Activite : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activite)

        var listview = findViewById<ListView>(R.id.listView)

        var list = mutableListOf<Model>()


        list.add(Model("Cuisine","......",R.drawable.cuisine))
        list.add(Model("Amitié","Fottball...",R.drawable.amitie))
        list.add(Model("Informatique","Fottball...",R.drawable.informatique))
        list.add(Model("Sport","Fottball...",R.drawable.sport))
        list.add(Model("Jeux Video","Fottball...",R.drawable.jeux))
        list.add(Model("Voyage","Fottball...",R.drawable.voyage))
        list.add(Model("Musique","Fottball...",R.drawable.musique))



        listview.adapter = Myadapter(this,R.layout.row,list)

        listview.setOnItemClickListener {  parent:AdapterView<*>, view:View, position:Int, id:Long ->

            if(position == 0){
                Toast.makeText(this,"Clicked",Toast.LENGTH_SHORT).show()
            }
            if(position == 1){
                Toast.makeText(this,"Clicked",Toast.LENGTH_SHORT).show()
            }
            if(position == 2){
                Toast.makeText(this,"Clicked",Toast.LENGTH_SHORT).show()
            }
            if(position == 3){
                Toast.makeText(this,"Clicked",Toast.LENGTH_SHORT).show()
            }


        }




    }
}