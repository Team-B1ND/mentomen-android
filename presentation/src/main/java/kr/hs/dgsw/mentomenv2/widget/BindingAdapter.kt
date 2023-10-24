package kr.hs.dgsw.mentomenv2.widget

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Button
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.mentomenv2.state.PostState
import kr.hs.dgsw.mentomenv2.util.getParentActivity

@BindingAdapter("designButtonState")
fun setDesignButtonState(view: Button, tagState: MutableLiveData<PostState>) {
    val parentActivity = view.getParentActivity() ?: return
    tagState.observe(parentActivity) {
        Log.d(TAG, "setDesignButtonState: $it")
    }
}