package com.example.mitmit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mitmit.models.User
import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        val user = intent.getParcelableExtra<User>("userInfos")

        name_txt.text = user?.displayName
        email_txt.text = user?.email
        interests_txt.text = user?.loisirs?.reduce { acc, s -> "$acc  $s" }
        phone_number_txt.text = user?.phone

        Glide.with(this)
            .load(user?.photoUrl)
            .into(profile_image)
    }
}