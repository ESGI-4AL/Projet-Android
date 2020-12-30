package com.example.mitmit.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class LoisirAdapter(private val loisirs:List<Loisirs>,private val onLoisirsClickedListener: OnLoisirsClickedListener) : RecyclerView.Adapter<LoisirViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoisirViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return LoisirViewHolder(inflater, parent)

    }

    override fun getItemCount(): Int = loisirs.size


    override fun onBindViewHolder(holder: LoisirViewHolder, position: Int) {

        val loisirs = loisirs[position]

        holder.bind(loisirs,onLoisirsClickedListener)


    }



}