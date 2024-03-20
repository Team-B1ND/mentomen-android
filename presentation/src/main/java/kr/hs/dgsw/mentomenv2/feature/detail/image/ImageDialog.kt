package kr.hs.dgsw.mentomenv2.feature.detail.image

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.Window
import androidx.databinding.DataBindingUtil
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.databinding.DialogImageDetailBinding

class ImageDialog(context: Context, image: Uri?) : Dialog(context) {
    private val imageRes: Uri?

    init {
        this.imageRes = image
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding: DialogImageDetailBinding = DataBindingUtil.inflate(layoutInflater, R.layout.dialog_image_detail, null, false)
        setContentView(binding.root)

    }
}