package app.axon.test.utils

import androidx.appcompat.widget.AppCompatImageView
import app.axon.test.R
import com.bumptech.glide.Glide

object ImageLoader {
    internal fun load(link: String?, imageView: AppCompatImageView) {
        val request = Glide.with(imageView.context)
                .load(link)
                .placeholder(R.drawable.ic_avatar_placeholder)
                .circleCrop()

        request.into(imageView)

    }
}