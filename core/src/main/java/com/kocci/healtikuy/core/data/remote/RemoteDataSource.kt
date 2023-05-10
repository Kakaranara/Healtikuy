package com.kocci.healtikuy.core.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.kocci.healtikuy.core.constant.RunningType
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

    suspend fun getUserDataForLeaderboards(): QuerySnapshot {
        return firestore.collection(FsCollection.USERS)
            .orderBy("points", Query.Direction.DESCENDING)
            .limit(20)
            .get()
            .await()
    }

    suspend fun getRunningLeaderboards(type: RunningType): QuerySnapshot {
        val runningType: String = when (type) {
            RunningType.Running100M -> "running_100"
            RunningType.Running200M -> "running_200"
            RunningType.Running400M -> "running_400"
        }

        return firestore.collection(FsCollection.USERS)
            .orderBy(runningType)
            .limit(20)
            .get()
            .await()
    }

    suspend fun getUserChallenges(uid: String): QuerySnapshot {
        return firestore.collection(FsCollection.USERS).document(uid)
            .collection(FsCollection.CHALLENGES)
            .get()
            .await()
    }

    suspend fun getChallengesData(cId: String): DocumentSnapshot {
        return firestore.collection(FsCollection.USER_CHALLENGES)
            .document(cId)
            .get()
            .await()
    }

    suspend fun getChallengesData(): QuerySnapshot {
        return firestore.collection(FsCollection.USER_CHALLENGES)
            .get()
            .await()
    }

    suspend fun updateChallengeData(uid: String, cId: String, isCompleted: Boolean) {
        val isCompletedData = hashMapOf<String, Any>("is_completed" to isCompleted)
        firestore.collection(FsCollection.USERS).document(uid)
            .collection(FsCollection.CHALLENGES).document(cId)
            .update(isCompletedData)
            .await()
    }

    suspend fun createUserChallenges(uid: String, newChallenges: List<String>) {
        val ref = firestore
            .collection(FsCollection.USERS)
            .document(uid)
            .collection(FsCollection.CHALLENGES)

        firestore.runBatch { batch ->
            newChallenges.forEach {
                val docsReference = ref.document(it)
                val defaultAttr = hashMapOf("is_completed" to false)
                batch.set(docsReference, defaultAttr)
            }
        }.await()
    }

    suspend fun updateUserData(uid: String, document: HashMap<String, Any>) {
        firestore.collection(FsCollection.USERS)
            .document(uid)
            .set(document)
            .await()
    }

    suspend fun updateAvatar(uid: String, newAvatar: String, newUsername: String) {
        val docs: HashMap<String, Any> = hashMapOf(
            "avatar" to newAvatar,
            "username" to newUsername
        )
        firestore.collection(FsCollection.USERS).document(uid).update(
            docs
        ).await()
    }

    suspend fun updateAfterBuyAvatar(uid: String, coin: Int, inventory: Set<String>) {
        val newData = hashMapOf(
            "coin" to coin, "inventory" to inventory.toList()
        )
        firestore.collection(FsCollection.USERS).document(uid).update(newData).await()
    }

}