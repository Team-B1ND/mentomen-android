package kr.hs.dgsw.mentomenv2.feature.detail.image

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.databinding.DialogImageDetailBinding

class ImageDialog(context: Context, image: String?) : Dialog(context) {
    private val imageRes: String?

    init {
        this.imageRes = image
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding: DialogImageDetailBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.dialog_image_detail, null, false)
        setContentView(binding.root)
        Picasso.get().load(imageRes).into(binding.photoView)
        binding.photoView.scaleType = ImageView.ScaleType.CENTER_INSIDE
    }
}
