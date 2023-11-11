package kr.hs.dgsw.mentomenv2.adapter.callback

import android.net.Uri
import androidx.recyclerview.widget.DiffUtil

object ImageDiffUtil : DiffUtil.ItemCallback<Uri?>() {
    override fun areItemsTheSame(oldItem: Uri, newItem: Uri): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: Uri, newItem: Uri): Boolean = oldItem == newItem
}
