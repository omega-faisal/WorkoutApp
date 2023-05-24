package eu.tutorials.workout07

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Insert
    suspend fun InsertExercise(Historyentity:HistoryEntity)

    @Query("SELECT * FROM `HISTORY-TABLE`")
    fun fetchAllDates():Flow<List<HistoryEntity>>
    // here suspend will not be used since it is returning a flow
}