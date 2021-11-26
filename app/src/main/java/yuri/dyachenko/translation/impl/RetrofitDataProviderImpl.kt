package yuri.dyachenko.translation.impl

import yuri.dyachenko.translation.api.SkyEngApi
import yuri.dyachenko.translation.model.DataProvider

class RetrofitDataProviderImpl(private val api: SkyEngApi) : DataProvider {

    override fun search(wordToSearch: String) = api.search(wordToSearch)
}