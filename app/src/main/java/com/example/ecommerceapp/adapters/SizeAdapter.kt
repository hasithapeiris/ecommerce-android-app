package com.example.ecommerceapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.databinding.SizeItemBinding

class SizeAdapter(
    private val context: Context,
    private val list: List<String>,
    private val onClickSize: SizeOnClickInterface
): RecyclerView.Adapter<SizeAdapter.ViewHolder>() {

    private  var selectedItem :  Int = -1
    private  var lastSelectedItem :  Int = -1

    inner class ViewHolder(val binding: SizeItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(SizeItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.btnSizeItem.text = list[position]

        holder.binding.btnSizeItem.setOnClickListener {
            onClickSize.onClickSize(holder.binding.btnSizeItem ,position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

interface SizeOnClickInterface{
    fun  onClickSize(button: Button, position:Int)
}