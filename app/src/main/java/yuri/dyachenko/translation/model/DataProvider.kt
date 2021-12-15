package yuri.dyachenko.translation.model

import yuri.dyachenko.model.Word

interface DataProvider {

    suspend fun search(wordToSearch: String): List<Word>
}