package com.example.mitmit.daos

import com.example.mitmit.models.User
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserDao {
    private val db = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("users")

    fun addUser(user: User?) {
        user?.let {
            GlobalScope.launch(Dispatchers.IO) {
                usersCollection.document(user.uid).set(it)
            }
        }
    }

    fun editPhoneNumber(user: FirebaseUser?, phone: String) {
        user?.let {
            GlobalScope.launch(Dispatchers.IO) {
                usersCollection.document(user.uid).update("phone", phone)
            }
        }
    }

    fun editLoisirs(user: FirebaseUser?, loisirsOfUser: MutableList<String?>) {
        user?.let {
            GlobalScope.launch(Dispatchers.IO) {
                usersCollection.document(user.uid).update("loisirs", loisirsOfUser)
            }
        }
    }
}