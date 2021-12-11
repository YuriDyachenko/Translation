package yuri.dyachenko.translation.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import yuri.dyachenko.translation.model.HISTORY_TABLE_NAME
import yuri.dyachenko.translation.model.HistoryEntity

@Dao
interface HistoryDao {

    @Query("SELECT * FROM $HISTORY_TABLE_NAME")
    suspend fun all(): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: HistoryEntity)
}