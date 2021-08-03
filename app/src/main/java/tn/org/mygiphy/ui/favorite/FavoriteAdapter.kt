package tn.org.mygiphy.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tn.org.mygiphy.databinding.FavoriteItemLayoutBinding
import tn.org.mygiphy.model.GifItem

class FavoriteAdapter(val ItemClick: (GifItem) -> Unit) :
    ListAdapter<GifItem, FavoriteAdapter.FavoriteViewHolder>(GifsTrendUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = FavoriteItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavoriteViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
            holder.binding.delete.setOnClickListener {
                ItemClick(item)
            }
        }
    }


    inner class FavoriteViewHolder(val binding: FavoriteItemLayoutBinding) :
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