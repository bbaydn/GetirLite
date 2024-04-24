package com.getir.patika.getirlite.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.getir.patika.getirlite.databinding.DetailedProductBinding

class DetailAdapter(var mContext: Context):RecyclerView.Adapter<DetailAdapter.DetailHolder>() {

    inner class DetailHolder(binding: DetailedProductBinding): RecyclerView.ViewHolder(binding.root) {
        private var binding: DetailedProductBinding
        init {
            this.binding = binding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding = DetailedProductBinding.inflate(layoutInflater, parent, false)
        return DetailHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return 1
    }
}