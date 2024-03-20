package kr.hs.dgsw.mentomenv2.util

import android.content.Context
import android.content.ContextWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity

fun View.getParentActivity(): AppCompatActivity? {
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

fun View.dpToPx(dp: Float): Float {
    val density = context.resources.displayMetrics.density
    return dp * density
}

fun dpToPx(context: Context, dp: Float): Float {
    val density = context.resources.displayMetrics.density
    return dp * density
}
