package kr.hs.dgsw.mentomenv2.feature.detail.image

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.databinding.DialogImageDetailBinding

class ImageDialog(context: Context) : Dialog(context) {
    val imageRes: String = "https://mentomen.s3.ap-northeast-2.amazonaws.com/005add6a-b2dd-48ce-b1bf-6e6a236d8626.jpeg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding: DialogImageDetailBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.dialog_image_detail, null, false)
        setContentView(binding.root)
        Glide.with(this.context)
            .load(imageRes)
            .into(binding.photoView)
    }
}
