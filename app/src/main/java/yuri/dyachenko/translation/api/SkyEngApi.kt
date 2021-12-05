package yuri.dyachenko.translation.api

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import yuri.dyachenko.translation.model.Word

interface SkyEngApi {

    @GET("words/search")
    fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<Word>>
}