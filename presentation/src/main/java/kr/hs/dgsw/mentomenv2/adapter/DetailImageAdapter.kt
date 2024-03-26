package kr.hs.dgsw.mentomenv2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.databinding.ItemDetailImageBinding
import kr.hs.dgsw.mentomenv2.domain.model.ImgUrl
import kr.hs.dgsw.mentomenv2.domain.util.Log
import kr.hs.dgsw.mentomenv2.feature.detail.image.ImageDialog

class DetailImageAdapter(
    private val imageList: List<String?>,
) : RecyclerView.Adapter<DetailImageAdapter.ImageDetailViewHolder>() {
    inner class ImageDetailViewHolder(private val binding: ItemDetailImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String?) {
            Glide.with(binding.root.context)
                .load(item)
                .into(binding.imgUrl)

            binding.imgUrl.setOnClickListener {
                Log.d("ImageAdapter", "ImageClicked")
                val dialog = ImageDialog(binding.root.context, item)
                dialog.show()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ImageDetailViewHolder {
        return ImageDetailViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_detail_image,
                parent,
                false,
            ),
        )
    }

    override fun onBindViewHolder(
        holder: ImageDetailViewHolder,
        position: Int,
    ) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size
}
