package yuri.dyachenko.translation.model

import com.google.gson.annotations.SerializedName

data class Word(
    @SerializedName("text")
    val text: String?,
    @SerializedName("meanings")
    val meanings: List<Meaning>?
)

fun meaningsToString(meanings: List<Meaning>?) = meanings?.let {
    val sb = StringBuilder()
    meanings.forEach {
        it.translation?.let { translation ->
            if (sb.isNotEmpty()) {
                sb.append(", ")
            }
            sb.append(translation.text)
        }
    }
    sb.toString()
} ?: ""
