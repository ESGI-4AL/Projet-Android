package com.example.mitmit.nearby_users_recyclerview

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mitmit.DashboardActivity
import com.example.mitmit.R
import com.example.mitmit.UserDetailsActivity
import com.example.mitmit.Utils.PermissionUtils
import com.example.mitmit.daos.UserDao
import com.example.mitmit.models.User
import com.google.android.gms.location.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.ktx.toObject
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_users.*
import org.imperiumlabs.geofirestore.GeoFirestore
import org.imperiumlabs.geofirestore.extension.getAtLocation
import org.imperiumlabs.geofirestore.extension.getLocation
import java.io.IOException

class UsersActivity : AppCompatActivity(), OnUserClicked {

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 999
        private const val RADIUS = 500.0
    }

    private val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
    private val collectionRef = FirebaseFirestore.getInstance().collection("users")
    private val geoFirestore = GeoFirestore(collectionRef)
    private val docRef = FirebaseFirestore
                                        .getInstance()
                                        .collection("users")
                                        .document(currentUserId!!)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        indicator.visibility = View.VISIBLE

        swipeRefreshLayout.setOnRefreshListener {
            indicator.visibility = View.GONE
            swipeRefreshLayout.isRefreshing = true
            loadUsers()
            swipeRefreshLayout.isRefreshing = false
        }

        btn_my_profile.setOnClickListener {
            val dashboardIntent = Intent(this, DashboardActivity::class.java)
            startActivity(dashboardIntent)
        }
    }

    private fun loadUsers() {
        docRef.get()
            .addOnSuccessListener { documentSnapshot ->
                val location: GeoPoint = documentSnapshot.get("l") as GeoPoint
                geoFirestore.getAtLocation(location, RADIUS) { docs, ex ->
                    if (ex != null) {
                        Log.e("users activity", "onError: ", ex)
                        return@getAtLocation
                    } else {
                        if (docs != null) {
                            val users = docs.map {
                                it.toObject<User>()
                            }.filter {
                                !it?.uid.equals(currentUserId)
                            }.toList()
                            Log.d("users activity here", "$users")
                            users_recyclerView?.apply {
                                layoutManager = LinearLayoutManager(this@UsersActivity)
                                adapter = UsersAdapter(users, this@UsersActivity)
                                }
                            }
                        }
                    }
            }
    }

    @SuppressLint("MissingPermission")
    private fun setUpLocationListener() {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        // for getting the current location update after every 2 seconds with high accuracy
        val locationRequest = LocationRequest().setInterval(30 * 1000).setFastestInterval(5 * 1000)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)

        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    for (location in locationResult.locations) {
                        try {
                            val mAuth = FirebaseAuth.getInstance()
                            val currentUser = mAuth.currentUser
                            val userDao = UserDao()
                            userDao.editLocation(currentUser, geoFirestore, location)
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            },
            Looper.myLooper()
        )
    }

    override fun onStart() {
        super.onStart()
        when {
            PermissionUtils.isAccessFineLocationGranted(this) -> {
                when {
                    PermissionUtils.isLocationEnabled(this) -> {
                        setUpLocationListener()
                    }
                    else -> {
                        PermissionUtils.showGPSNotEnabledDialog(this)
                    }
                }
            }
            else -> {
                PermissionUtils.requestAccessFineLocationPermission(
                    this,
                    LOCATION_PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    when {
                        PermissionUtils.isLocationEnabled(this) -> {
                            setUpLocationListener()
                        }
                        else -> {
                            PermissionUtils.showGPSNotEnabledDialog(this)
                        }
                    }
                } else {
                    Toast.makeText(
                        this,
                        getString(R.string.location_permission_not_granted),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onUserClicked(user: User?)
    {
        val userDetailsIntent = Intent(this, UserDetailsActivity::class.java)
        userDetailsIntent.putExtra("userInfos", user)
        startActivity(userDetailsIntent)
    }
}