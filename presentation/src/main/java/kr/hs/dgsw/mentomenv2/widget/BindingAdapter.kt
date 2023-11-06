package kr.hs.dgsw.mentomenv2.widget

import android.widget.Button
import androidx.databinding.BindingAdapter
import kr.hs.dgsw.mentomenv2.R

@BindingAdapter("designButtonState")
fun setDesignButtonState(view: Button, tagState: String) {
    if (tagState == "DESIGN" || tagState == "ALL") {
        view.setBackgroundResource(R.drawable.bg_selected_design)
    } else {
        view.setBackgroundResource(R.drawable.bg_unselected_tag)
    }
}

@BindingAdapter("webButtonState")
fun setWebButtonState(view: Button, tagState: String) {
    if (tagState == "WEB" || tagState == "ALL") {
        view.setBackgroundResource(R.drawable.bg_selected_web)
    } else {
        view.setBackgroundResource(R.drawable.bg_unselected_tag)
    }
}

@BindingAdapter("androidButtonState")
fun setAndroidButtonState(view: Button, tagState: String) {
    if (tagState == "ANDROID" || tagState == "ALL") {
        view.setBackgroundResource(R.drawable.bg_selected_android)
    } else {
        view.setBackgroundResource(R.drawable.bg_unselected_tag)
    }
}

@BindingAdapter("serverButtonState")
fun setServerButtonState(view: Button, tagState: String) {
    if (tagState == "SERVER" || tagState == "ALL") {
        view.setBackgroundResource(R.drawable.bg_selected_server)
    } else {
        view.setBackgroundResource(R.drawable.bg_unselected_tag)
    }
}

@BindingAdapter("iosButtonState")
fun setIosButtonState(view: Button, tagState: String) {
    if (tagState == "IOS" || tagState == "ALL") {
        view.setBackgroundResource(R.drawable.bg_selected_ios)
    } else {
        view.setBackgroundResource(R.drawable.bg_unselected_tag)
    }
}
