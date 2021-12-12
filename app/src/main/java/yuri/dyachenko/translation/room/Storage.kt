package yuri.dyachenko.translation.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import yuri.dyachenko.translation.model.DataConverter
import yuri.dyachenko.translation.model.HistoryEntity
import yuri.dyachenko.translation.model.dao.HistoryDao

@Database(
    exportSchema = false,
    entities = [HistoryEntity::class],
    version = 2
)
@TypeConverters(DataConverter::class)
abstract class Storage : RoomDatabase() {

    abstract fun historyDao(): HistoryDao
}