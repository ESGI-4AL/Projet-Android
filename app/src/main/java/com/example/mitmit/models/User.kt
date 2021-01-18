package com.example.mitmit.models

import android.provider.ContactsContract

data class User(val uid: String = "",
                val displayName: String? = "",
                val email: String? = "",
                val phone: String = "",
                val loisirs: List<String> = emptyList())
