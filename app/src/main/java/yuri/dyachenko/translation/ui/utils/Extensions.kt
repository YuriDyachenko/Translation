package yuri.dyachenko.translation.ui.utils

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import yuri.dyachenko.translation.App
import java.text.SimpleDateFormat
import java.util.*

const val DATE_TIME_FORMAT = "dd.MMM.yy HH:mm:ss"

fun Date.format(): String = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault())
    .format(this)

fun String.parseDate(): Date = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault())
    .parse(this) ?: Date()

val Context.app: App
    get() = applicationContext as App

val Fragment.app: App
    get() = requireContext().app

fun View.show() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.hide() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}
