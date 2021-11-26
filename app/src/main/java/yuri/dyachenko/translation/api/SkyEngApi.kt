package yuri.dyachenko.translation.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import yuri.dyachenko.translation.model.Word

interface SkyEngApi {

    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Single<List<Word>>
}