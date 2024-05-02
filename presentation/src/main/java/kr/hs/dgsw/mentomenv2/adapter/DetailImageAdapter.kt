package kr.hs.dgsw.mentomenv2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.adapter.callback.ImageDiffUtil
import kr.hs.dgsw.mentomenv2.base.BaseListAdapter
import kr.hs.dgsw.mentomenv2.databinding.ItemDetailImageBinding
import kr.hs.dgsw.mentomenv2.domain.util.Log
import kr.hs.dgsw.mentomenv2.feature.detail.image.ImageDialog
import kr.hs.dgsw.mentomenv2.util.dialogResize

class DetailImageAdapter : BaseListAdapter<String, ItemDetailImageBinding>(R.layout.item_detail_image, ImageDiffUtil) {

    override fun action(item: String, binding: ItemDetailImageBinding) {
        Glide.with(binding.root.context)
            .load(item)
            .into(binding.imgUrl)

        binding.imgUrl.setOnClickListener {
            Log.d("ImageAdapter", "ImageClicked")
            val dialog = ImageDialog(binding.root.context)
            dialog.show()
        }
    }
}
