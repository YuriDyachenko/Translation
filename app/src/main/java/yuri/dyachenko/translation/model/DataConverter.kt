package yuri.dyachenko.translation.model

import androidx.room.TypeConverter
import yuri.dyachenko.translation.ui.utils.format
import yuri.dyachenko.translation.ui.utils.parseDate
import java.io.Serializable
import java.util.*

class DataConverter : Serializable {

    @TypeConverter
    fun fromDate(date: Date): String = date.format()

    @TypeConverter
    fun toDate(text: String): Date = text.parseDate()
}