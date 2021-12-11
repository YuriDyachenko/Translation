package yuri.dyachenko.translation.impl

import yuri.dyachenko.translation.model.HistoryDataProvider
import yuri.dyachenko.translation.model.HistoryEntity
import yuri.dyachenko.translation.room.Storage
import java.util.*

class RoomHistoryDataProviderImpl(private val storage: Storage) : HistoryDataProvider {

    override suspend fun getAllHistory(): List<HistoryEntity> =
        storage.historyDao().all()

    override suspend fun saveHistory(word: String) =
        storage.historyDao().insert(HistoryEntity(0, word, Date()))
}