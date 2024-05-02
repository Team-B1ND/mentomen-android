package kr.hs.dgsw.mentomenv2.adapter.callback

import androidx.recyclerview.widget.DiffUtil

object ImageDiffUtil : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(
        oldItem: String,
        newItem: String,
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: String,
        newItem: String,
    ): Boolean = oldItem == newItem
}
