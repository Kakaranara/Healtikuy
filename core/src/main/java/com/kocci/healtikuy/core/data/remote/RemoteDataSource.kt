package com.kocci.healtikuy.core.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val rootRef: FirebaseFirestore,
) {

    fun getFirebaseUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun getFirebaseAuth(): FirebaseAuth {
        return firebaseAuth
    }

}