package yuri.dyachenko.translation.model

import com.google.gson.annotations.SerializedName

data class Meaning(
    @SerializedName("text")
    val text: String?,
    @SerializedName("translation")
    val translation: Translation?,
    @field:SerializedName("imageUrl")
    val imageUrl: String?
)
