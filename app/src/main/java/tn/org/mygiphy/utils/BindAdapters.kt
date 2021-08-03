package tn.org.mygiphy.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import tn.org.mygiphy.R
import tn.org.mygiphy.data.remote.model.GiphyModel
import tn.org.mygiphy.model.GifItem


@BindingAdapter("gif_url")
fun loadGif(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .fitCenter()
        .placeholder(R.drawable.ic_download_image)
        .error(R.drawable.ic_error)
        .into(view)
}

@BindingAdapter("is_favorite")
fun isFavorite(imageView: ImageView, isFavorite: Boolean) {
    val id = if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_not_favorite
    Glide.with(imageView)
        .load(id)
        .placeholder(R.drawable.ic_download_image)
        .error(R.drawable.ic_error)
        .into(imageView)
}

fun GiphyModel.asGifItemList(): List<GifItem> {
    return this.data.map {
        GifItem(
            id = it.id,
            title = it.title,
            url = it.images.downsized.url
        )
    }

}