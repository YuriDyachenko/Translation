package yuri.dyachenko.translation.ui.base

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import yuri.dyachenko.translation.R
import yuri.dyachenko.translation.showSnackBar

abstract class BaseFragment(
    contentLayoutId: Int,
    private val blockBackPressed: Boolean = false
) : Fragment(contentLayoutId) {

    private val backPressedCallback = object : OnBackPressedCallback(false) {
        override fun handleOnBackPressed() {
        }
    }

    private val snackBarCallback = object : Snackbar.Callback() {

        override fun onShown(sb: Snackbar?) {
            super.onShown(sb)
            backPressedCallback.isEnabled = true
        }

        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
            super.onDismissed(transientBottomBar, event)
            backPressedCallback.isEnabled = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (blockBackPressed) {
            requireActivity()
                .onBackPressedDispatcher
                .addCallback(this, backPressedCallback)
        }
    }

    fun showErrorSnackBar(view: View, e: Throwable?, action: (View) -> Unit) {
        view.showSnackBar(
            e?.message ?: getString(R.string.something_broke),
            R.string.reload,
            snackBarCallback,
            action
        )
    }
}