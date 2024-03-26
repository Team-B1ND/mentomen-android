package kr.hs.dgsw.mentomenv2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.adapter.callback.ImageDiffUtil
import kr.hs.dgsw.mentomenv2.databinding.ItemImageBinding

class ImageAdapter : ListAdapter<String?, ImageAdapter.ImageViewHolder>(ImageDiffUtil) {
    inner class ImageViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String?) {
            Glide.with(binding.cardView.context)
                .load(item)
                .into(binding.image)
            binding.btnCancel.setOnClickListener {
                submitList(currentList.filter { it != item })
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ImageViewHolder {
        return ImageViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_image,
                parent,
                false,
            ),
        )
    }

    override fun onBindViewHolder(
        holder: ImageViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }
}
