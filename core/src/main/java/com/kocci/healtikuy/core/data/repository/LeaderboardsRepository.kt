package com.kocci.healtikuy.core.data.repository

import com.kocci.healtikuy.core.data.remote.RemoteDataSource
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.model.leaderboards.LeaderboardsAttr
import com.kocci.healtikuy.core.domain.repository.ILeaderboardsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LeaderboardsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : ILeaderboardsRepository {

    /**
     * For now, leaderboards just accept a remote data source (firestore),
     * However, now im using firestore, so data have cached automatically.
     * at another time, when remote data source is changed (maybe mongo or ktor?), this should depend on local source.
     * Remote source -> save to local -> emit local
     */

    override fun getLeaderboards(): Flow<Async<List<LeaderboardsAttr>>> = flow {
        emit(Async.Loading)
        try {
            val remoteList = remoteDataSource.getUserDataForLeaderboards().documents
            val leaderList = remoteList.map {
                val data = it.data!! //! my app guarantees non-null in firestore (for now).
                LeaderboardsAttr(
                    name = data["username"] as String,
                    avatar = data["avatar"] as String,
                    points = data["points"] as Long,
                    data["running_100"] as Long,
                    data["running_200"] as Long,
                    data["running_400"] as Long
                )
            }
            emit(Async.Success(leaderList))
        } catch (e: Exception) {
            emit(Async.Error(e.message.toString()))
        }
    }
}