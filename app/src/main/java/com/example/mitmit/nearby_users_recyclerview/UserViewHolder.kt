package com.example.mitmit.nearby_users_recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mitmit.R
import com.example.mitmit.models.User
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_dashboard.*

class UserViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.user_item, parent, false)), View.OnClickListener {
    private var nameTextView: TextView? = null
    private var emailTextView: TextView? = null
    private var profileImage: CircleImageView? = null
    private var user: User? = null
    private var onUserClicked: OnUserClicked? = null

    init {
        nameTextView = itemView.findViewById(R.id.name_txt)
        emailTextView = itemView.findViewById(R.id.email)
        profileImage = itemView.findViewById(R.id.profile_image)
    }

    fun bind(user: User, onUserClicked: OnUserClicked) {
        this.user = user
        this.onUserClicked = onUserClicked

        nameTextView?.text = user.displayName
        emailTextView?.text = user.email
        Glide.with(this.itemView)
            .load(user.photoUrl)
            .into(profileImage!!)
        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        onUserClicked?.onUserClicked(user)
    }
}