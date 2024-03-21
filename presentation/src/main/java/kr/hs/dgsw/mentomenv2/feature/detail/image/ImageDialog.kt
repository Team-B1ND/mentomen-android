package kr.hs.dgsw.mentomenv2.feature.detail.image

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.databinding.DialogImageDetailBinding
import retrofit2.http.Url

class ImageDialog(context: Context, image: String?) : Dialog(context) {
    private val imageRes: String?

    init {
        this.imageRes = image
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ImageDialog", "res: $imageRes")
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding: DialogImageDetailBinding = DataBindingUtil.inflate(layoutInflater, R.layout.dialog_image_detail, null, false)
        setContentView(binding.root)
        Picasso.get().load(imageRes).into(binding.photoView)
        binding.photoView.scaleType = ImageView.ScaleType.CENTER_INSIDE
        binding.photoView.minimumWidth = ViewGroup.LayoutParams.MATCH_PARENT
    }
}