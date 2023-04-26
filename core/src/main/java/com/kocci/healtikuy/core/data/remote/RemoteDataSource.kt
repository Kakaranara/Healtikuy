package com.kocci.healtikuy.core.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.kocci.healtikuy.core.data.remote.firestore.FsCollection
import com.kocci.healtikuy.core.service.FirstTimeService
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) {

    fun getFirebaseUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun getFirebaseAuth(): FirebaseAuth {
        return firebaseAuth
    }

    fun getFirestore(): FirebaseFirestore {
        return firestore
    }

    suspend fun createUserDataFirstTime(uid: String, username: String) {
        val document = FirstTimeService.getFirstTimeAttributes()
        document["username"] = username
        firestore.collection(FsCollection.USERS)
            .document(uid)
            .set(document)
            .await()
    }

    suspend fun getUserData(uid: String): DocumentSnapshot {
        return firestore.collection(FsCollection.USERS)
            .document(uid)
            .get()
            .await()
    }

    suspend fun updateUserData(uid: String, document: HashMap<String, Any>) {
        firestore.collection(FsCollection.USERS)
            .document(uid)
            .set(document)
            .await()
    }

    suspend fun updateAvatar(uid: String, newAvatar: String) {
        val avatar: HashMap<String, Any> = hashMapOf("avatar" to newAvatar)
        firestore.collection(FsCollection.USERS).document(uid).update(
            avatar
        ).await()
    }

    suspend fun updateAfterBuyAvatar(uid: String, coin: Int, inventory: Set<String>) {
        val newData = hashMapOf(
            "coin" to coin, "inventory" to inventory.toList()
        )
        firestore.collection(FsCollection.USERS).document(uid).update(newData).await()
    }

}