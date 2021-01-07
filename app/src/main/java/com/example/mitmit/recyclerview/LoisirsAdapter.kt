package com.example.mitmit.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LoisirsAdapter(private val loisirs:List<Loisir>, private val onLoisirClicked: OnLoisirClicked) :
    RecyclerView.Adapter<LoisirViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoisirViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return LoisirViewHolder(inflater, parent)

    }

    override fun getItemCount(): Int = loisirs.size


    override fun onBindViewHolder(holder: LoisirViewHolder, position: Int) {
        val loisir = loisirs[position]
        holder.bind(loisir, onLoisirClicked)
    }
}