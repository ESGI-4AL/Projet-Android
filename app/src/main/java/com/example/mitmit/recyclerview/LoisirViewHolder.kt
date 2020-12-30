package com.example.mitmit.recyclerview

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mitmit.MainActivity
import com.example.mitmit.R

class LoisirViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.row, parent, false)), View.OnClickListener {
    private var textView1:TextView? = null
    private var textView2:TextView? = null
    private var image:ImageView? = null
    private  var loisirs: Loisirs? = null
    private  var onLoisirsClickedListener:OnLoisirsClickedListener?=null

    init {

        textView1 = itemView.findViewById(R.id.textView1)
        textView2 = itemView.findViewById(R.id.textView2)
        image = itemView.findViewById(R.id.image)

    }


    fun bind(loisirs:Loisirs,onLoisirsClickedListener: OnLoisirsClickedListener) {
        this.loisirs = loisirs
        this.onLoisirsClickedListener=onLoisirsClickedListener
        textView1?.text = loisirs.title
        textView2?.text = loisirs.description
        image?.setImageResource(loisirs.img)

        itemView.setOnClickListener(this)

}

    override fun onClick(v: View?) {


        onLoisirsClickedListener?.onLoisirsClicked(loisirs)

     }


}