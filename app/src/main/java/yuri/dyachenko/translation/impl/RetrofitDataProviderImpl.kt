package yuri.dyachenko.translation.impl

import yuri.dyachenko.translation.api.SkyEngApi
import yuri.dyachenko.translation.model.DataProvider
import yuri.dyachenko.translation.model.Word

class RetrofitDataProviderImpl(private val api: SkyEngApi) : DataProvider {

    override suspend fun search(wordToSearch: String): List<Word> =
        api.searchAsync(wordToSearch).await()
}