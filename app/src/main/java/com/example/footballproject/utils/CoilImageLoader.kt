package com.example.footballproject.utils

import android.widget.ImageView
import coil.decode.SvgDecoder
import coil.request.ImageRequest

object CoilImageLoader {

     fun ImageView.loadImage(path: String) {
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

