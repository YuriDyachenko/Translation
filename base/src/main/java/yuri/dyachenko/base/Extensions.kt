package yuri.dyachenko.base

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(
    text: String,
    actionTextId: Int,
    callback: Snackbar.Callback,
    action: (View) -> Unit
) {
    Snackbar.make(this, text, Snackbar.LENGTH_INDEFINITE)
        .setAction(actionTextId, action)
        .addCallback(callback)
        .show()
}

