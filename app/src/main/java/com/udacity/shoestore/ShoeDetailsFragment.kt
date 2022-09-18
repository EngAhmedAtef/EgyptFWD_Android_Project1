package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeViewModel

class ShoeDetailsFragment : Fragment() {
    private lateinit var binding: FragmentShoeDetailsBinding
    private lateinit var shoeViewModel: ShoeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)
        shoeViewModel = ViewModelProvider(this.requireActivity()).get(ShoeViewModel::class.java)
        binding.shoe = shoeViewModel.shoe.value
        // Buttons navigation
        binding.shoeSaveButton.setOnClickListener {
            shoeViewModel.addShoe()
            findNavController().navigate(R.id.action_shoeDetailsFragment_to_shoeListFragment)
        }
        binding.shoeCancelButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_shoeDetailsFragment_to_shoeListFragment))
        return binding.root
    }
}