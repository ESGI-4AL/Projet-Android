package com.example.mitmit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.mitmit.daos.UserDao
import com.example.mitmit.models.User
import com.example.mitmit.recyclerview.LoisirsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("users")
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        val currentUserId = currentUser?.uid
        val docRef = usersCollection.document(currentUserId!!)

        phone_number_txt.isEnabled = false

        docRef.get().addOnSuccessListener { documentSnapshot ->
            Log.d("issue1", "${documentSnapshot.data}")
            user = documentSnapshot.toObject<User>()
            Log.d("issue2", user.toString())
            name_txt.text = user?.displayName
            email_txt.text = user?.email
            interests_txt.text = user?.loisirs?.reduce { acc, s -> "$acc  $s" }
            if(!user?.phone.equals("")){
                phone_number_txt.hint = user?.phone
            }
        }

        Glide.with(this).load(currentUser.photoUrl).into(profile_image)

        sign_out_btn.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

        phone_edit_btn.setOnClickListener {
            val state = phone_number_txt.isEnabled
            if (!state) {
                phone_number_txt.isEnabled = true
            } else {
                val userDao = UserDao()
                userDao.editPhoneNumber(currentUser, phone_number_txt.text.toString())
                phone_number_txt.hint = user?.phone
                phone_number_txt.isEnabled = false
            }
        }

        interests_edit_btn.setOnClickListener {
            val loisirsIntent = Intent(this, LoisirsActivity::class.java)
            startActivity(loisirsIntent)
        }
    }
}