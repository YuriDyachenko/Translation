package yuri.dyachenko.translation.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

const val HISTORY_TABLE_NAME = "history"

@Entity(tableName = HISTORY_TABLE_NAME)
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "word")
    val word: String,

    @ColumnInfo(name = "searchDate")
    val searchDate: Date
)
