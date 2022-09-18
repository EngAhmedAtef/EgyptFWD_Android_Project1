package com.udacity.shoestore

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imgUrl")
fun addShoeImage(view: ImageView, imageUrl: String) {
    if(imageUrl.isNotEmpty()) {
        Glide.with(view.context).load(imageUrl).placeholder(R.drawable.ic_launcher_background).into(view)
    }
}