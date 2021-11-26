package yuri.dyachenko.translation.model

import io.reactivex.Single

interface DataProvider {

    fun search(wordToSearch: String): Single<List<Word>>
}