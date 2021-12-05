package yuri.dyachenko.translation.model

interface DataProvider {

    suspend fun search(wordToSearch: String): List<Word>
}