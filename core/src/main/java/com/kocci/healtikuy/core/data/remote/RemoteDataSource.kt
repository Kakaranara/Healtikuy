package com.kocci.healtikuy.core.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val rootRef: FirebaseFirestore,
) {

    fun isUserLogin(): Boolean {
        return firebaseAuth.currentUser != null
    }
    /**
     * Firestore doesn't need an instance like Dao's in room database
     * Instead, we define remote data source in Firestore repository.
     */
}