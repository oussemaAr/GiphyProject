package tn.org.mygiphy.ui.trending

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tn.org.mygiphy.databinding.TrendingItemLayoutBinding
import tn.org.mygiphy.model.GifItem

class TrendingAdapter :
    PagingDataAdapter<GifItem, TrendingAdapter.TrendingViewHolder>(GifsTrendUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val binding = TrendingItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TrendingViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) holder.bind(item)
    }


    inner class TrendingViewHolder(private val binding: TrendingItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GifItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}

object GifsTrendUtils : DiffUtil.ItemCallback<GifItem>() {
    override fun areItemsTheSame(oldItem: GifItem, newItem: GifItem) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: GifItem, newItem: GifItem) = oldItem == newItem

}