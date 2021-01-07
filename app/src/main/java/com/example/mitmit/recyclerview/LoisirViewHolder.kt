package com.example.mitmit.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mitmit.R

class LoisirViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.row, parent, false)), View.OnClickListener {

    private var textView1:TextView? = null
    private var textView2:TextView? = null
    private var image:ImageView? = null
    private var loisir: Loisir? = null
    private var onLoisirClicked : OnLoisirClicked? = null

    init {
        textView1 = itemView.findViewById(R.id.textView1)
        textView2 = itemView.findViewById(R.id.textView2)
        image = itemView.findViewById(R.id.image)
    }

    fun bind(loisir: Loisir, onLoisirClicked: OnLoisirClicked) {
        this.loisir = loisir
        this.onLoisirClicked = onLoisirClicked
        textView1?.text = loisir.title
        image?.setImageResource(loisir.img)

        itemView?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        onLoisirClicked?.onLoisirsClicked(loisir)
    }
}