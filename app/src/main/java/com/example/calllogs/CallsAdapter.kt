package com.example.calllogs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.calllogs.databinding.ListItemBinding
import com.example.calllogs.models.Call




class CallsAdapter( val data: ArrayList<Call>):BaseAdapter() {
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position].type
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    LayoutInflater.from(parent!!.context)

        val binding =
                ListItemBinding.inflate(
                LayoutInflater.from(parent!!.context),
                parent,
                false
            )
            val call = data[position]
            binding.tvName.text = call.name
            binding.tvNumber.text = call.number
            when(call.type){
                "واردة" ->{
                    binding.imageView.setImageResource(R.drawable.call_received)
                }
                "صادرة" ->{
                    binding.imageView.setImageResource(R.drawable.call_out)

                }
                "لم يتم الرد عليها" ->{
                    binding.imageView.setImageResource(R.drawable.call_missed)

                }
            }

        return  binding.root

    }
}