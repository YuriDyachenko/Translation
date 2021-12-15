package yuri.dyachenko.model

import com.google.gson.annotations.SerializedName

data class Translation(
    @SerializedName("text")
    val text: String?,
    @SerializedName("note")
    val note: String?
)