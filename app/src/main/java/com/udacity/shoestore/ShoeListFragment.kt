package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginEnd
import androidx.core.view.marginTop
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.models.ShoeViewModel
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private lateinit var shoeViewModel: ShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        shoeViewModel = ViewModelProvider(this.requireActivity()).get(ShoeViewModel::class.java)
        binding.shoeDetailsFAB.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_shoeListFragment_to_shoeDetailsFragment))

        setHasOptionsMenu(true)

        shoeViewModel.shoeList.observe(viewLifecycleOwner, Observer {
            // Check if there are shoes in the list
            if(it.isNotEmpty()) {
                // If found shoes:
                // Iterate over the list, create a TextView, add the data to the TextView, add the TextView to the LinearLayout
                for (shoe in it) {
                    val shoeItemBinding = ShoeItemBinding.inflate(layoutInflater)
                    shoeItemBinding.shoe = shoe
                    binding.shoesLinearLayout.addView(shoeItemBinding.root)
                }
            } else {
                // The list is empty. Guide the user to add a shoe.
                val textView = TextView(this.context)
                textView.text = "Press the button below to start adding shoes."
                textView.textSize = 18F
                val layoutParameters: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                layoutParameters.setMargins(16,16,0,0)
                binding.shoesLinearLayout.addView(textView, layoutParameters)
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }
}