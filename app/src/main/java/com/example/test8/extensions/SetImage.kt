package com.example.test8.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.test8.R

fun ImageView.setImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(context).load(url).placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground)
            .into(this)
    } else {
        setImageResource(R.drawable.ic_launcher_foreground)
    }
}