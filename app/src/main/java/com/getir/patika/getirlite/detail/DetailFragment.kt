package com.getir.patika.getirlite.detail

import android.animation.ObjectAnimator
import android.content.res.Resources
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.getir.patika.getirlite.R
import com.getir.patika.getirlite.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
//        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.includedToolbar.closeButton.visibility = View.VISIBLE
        binding.includedToolbar.closeButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        val bundle: DetailFragmentArgs by navArgs()
        val addedFood = bundle.productInfo

        binding.productName.text = addedFood.name
        binding.price.text = addedFood.price.toString()
        Glide.with(binding.root.context)
            .load(addedFood.imageURL)
            .placeholder(R.drawable.def_image)
            .error(R.drawable.def_image)
            .into(binding.imageView2)

        binding.button.setOnClickListener {
            if(binding.includedToolbar.goToCart.visibility == View.VISIBLE) {
                binding.includedToolbar.tvPrice.text = "5₺"
            } else {
                toolbarItemVisible(true)
            }
        //    clickAddToCart(addedFood.id?.toInt(), addedFood.name, addedFood.price, addedFood.imageURL, 1)
        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:DetailViewModel by viewModels()
        viewModel = tempViewModel
    }

    private fun toolbarItemVisible(isVisible: Boolean) {
        val visibility = if (isVisible) View.VISIBLE else View.GONE
        val goToCart = binding.includedToolbar.goToCart
        goToCart.visibility = visibility

        val screenWidth = Resources.getSystem().displayMetrics.widthPixels
        val animator = ObjectAnimator.ofFloat(goToCart, "translationX", screenWidth.toFloat(), 0f)
        animator.duration = 500
        animator.interpolator = DecelerateInterpolator()
        animator.start()
    }

    private fun clickAddToCart (id:Int?, name:String?, price:Double?, imageUrl: String?, count: Int?){
        viewModel.addToCartVM(id, name, price, imageUrl, count)
    }


}