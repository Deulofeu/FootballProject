package com.example.footballproject

import android.widget.ImageView
import coil.decode.SvgDecoder
import coil.request.ImageRequest

interface ImageLoader {
    fun ImageView.loadImage(path: String)
}

object CoilImageLoader: ImageLoader {

    override fun ImageView.loadImage(path: String) {
        val imageLoader = coil.ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(this@loadImage.context)) }
            .build()

        val request = ImageRequest.Builder(this.context)
            .data(path)
            .target(this)
            .build()

        imageLoader.enqueue(request)
    }

}