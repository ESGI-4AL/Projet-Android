package com.example.mitmit.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mitmit.DashboardActivity
import com.example.mitmit.R
import com.example.mitmit.databinding.ActivityLoisirsBinding
import com.example.mitmit.databinding.ActivitySignInBinding
import kotlinx.android.synthetic.main.activity_loisirs.*

//modification
class LoisirsActivity : AppCompatActivity(), OnLoisirClicked{

    private  val loisirs = listOf<Loisir>(
        Loisir("Cuisine", R.drawable.cuisine),
        Loisir("Amitié", R.drawable.amitie),
        Loisir("Informatique", R.drawable.informatique),
        Loisir("Sport", R.drawable.sport),
        Loisir("Jeux Video", R.drawable.jeux),
        Loisir("Voyage", R.drawable.voyage),
        Loisir("Musique", R.drawable.musique)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loisirs)

        recyclerView?.apply {
            layoutManager = LinearLayoutManager(this@LoisirsActivity)
            adapter = LoisirsAdapter(loisirs, this@LoisirsActivity)
        }

        val binding = ActivityLoisirsBinding.inflate(layoutInflater)

        binding.btnValidate.setOnClickListener {
            val dashboardIntent = Intent(this, DashboardActivity::class.java)
            startActivity(dashboardIntent)
        }
    }

    override fun onLoisirsClicked(loisir: Loisir?) {
        //Log.d("toto",loisir.toString())
        //val dashboardIntent = Intent(this, DashboardActivity::class.java)
        //startActivity(dashboardIntent)
    }

}