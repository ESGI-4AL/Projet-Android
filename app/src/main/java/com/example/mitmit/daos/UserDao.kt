package com.example.mitmit.daos

import android.location.Location
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mitmit.models.User
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.imperiumlabs.geofirestore.GeoFirestore
import org.imperiumlabs.geofirestore.extension.setLocation

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

    fun editLocation(user: FirebaseUser?, geoFirestore: GeoFirestore,location: Location) {
        user?.let {
            GlobalScope.launch(Dispatchers.IO) {
                geoFirestore.setLocation(
                    it.uid,
                    GeoPoint(location.latitude, location.longitude)
                ) { exception ->
                    if (exception == null) {
                        Log.d("geoFireStore", "Location saved on server successfully!")
                    }
                    else {
                        Log.d("geoFireStore", "An error has occurred: $exception")
                    }
                }
            }
        }
    }
}