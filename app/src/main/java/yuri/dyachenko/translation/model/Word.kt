package yuri.dyachenko.translation.model

import com.google.gson.annotations.SerializedName

data class Word(
    @SerializedName("text")
    val text: String?,
    @SerializedName("meanings")
    val meanings: List<Meaning>?
)
