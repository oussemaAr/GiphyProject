package tn.org.mygiphy.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import tn.org.mygiphy.data.remote.model.GiphyModel
import tn.org.mygiphy.model.GifItem


@BindingAdapter("gif_url")
fun loadGif(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .fitCenter()
        .into(view)
}

@BindingAdapter("app:is_visible")
fun isVisible(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
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