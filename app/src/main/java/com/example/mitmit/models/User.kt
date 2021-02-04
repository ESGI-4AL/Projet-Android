package com.example.mitmit.models

import android.os.Parcel
import android.os.Parcelable

data class User(val uid: String = "",
                val displayName: String? = "",
                val email: String? = "",
                val phone: String = "",
                val photoUrl : String = "",
                val loisirs: List<String> = emptyList()
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createStringArrayList()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uid)
        parcel.writeString(displayName)
        parcel.writeString(email)
        parcel.writeString(phone)
        parcel.writeString(photoUrl)
        parcel.writeStringList(loisirs)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}