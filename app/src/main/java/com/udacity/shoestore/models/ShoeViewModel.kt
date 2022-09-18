package com.udacity.shoestore.models

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import timber.log.Timber

class ShoeViewModel : ViewModel() {
    // Create the live data for the shoe list
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList
    // Create the live data for the shoe object
    private val _shoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe>
        get() = _shoe
    // Create the init block
    init {
        // Initialize the mutable list
        _shoeList.value = mutableListOf()
        // Initialize the shoe object
        _shoe.value = Shoe()
    }

    fun addShoe() {
        val newShoe = Shoe()
        newShoe.name = shoe.value?.name.toString()
        newShoe.company = shoe.value?.company.toString()
        newShoe.description = shoe.value?.description.toString()
        newShoe.size = shoe.value?.size!!
        newShoe.imgUrl = shoe.value?.imgUrl.toString()
        _shoeList.value?.add(newShoe)
        shoe.value?.name = ""
        shoe.value?.company = ""
        shoe.value?.description = ""
        shoe.value?.size = 0.0
        shoe.value?.imgUrl = ""
    }
}