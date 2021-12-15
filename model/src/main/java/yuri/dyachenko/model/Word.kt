package yuri.dyachenko.model

import com.google.gson.annotations.SerializedName

data class Word(
    @SerializedName("text")
    val text: String?,
    @SerializedName("meanings")
    val meanings: List<Meaning>?
)
