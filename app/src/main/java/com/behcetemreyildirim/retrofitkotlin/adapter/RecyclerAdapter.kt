package com.behcetemreyildirim.retrofitkotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.behcetemreyildirim.retrofitkotlin.databinding.RowLayoutBinding
import com.behcetemreyildirim.retrofitkotlin.model.CryptoModel

class RecyclerAdapter(val cryptoList: ArrayList<CryptoModel>): RecyclerView.Adapter<RecyclerAdapter.RowHolder>() {
    class RowHolder(val binding: RowLayoutBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = RowLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return RowHolder(view)
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {

        holder.binding.textName.text = cryptoList[position].currency
        holder.binding.textPrice.text = cryptoList[position].price

    }

}