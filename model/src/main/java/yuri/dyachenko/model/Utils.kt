package yuri.dyachenko.model

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
