package yuri.dyachenko.translation.model

interface HistoryDataProvider {

    suspend fun getAllHistory(): List<HistoryEntity>
    suspend fun saveHistory(word: String)
}