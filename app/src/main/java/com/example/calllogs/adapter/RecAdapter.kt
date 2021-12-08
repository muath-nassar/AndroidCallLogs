package com.example.calllogs.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calllogs.R
import com.example.calllogs.database.MySqliteDB
import com.example.calllogs.databinding.ListItemBinding
import com.example.calllogs.models.Call

class RecAdapter(var data: ArrayList<Call>):RecyclerView.Adapter<RecAdapter.MyViewHolder>() {
    lateinit var context: Context
    class MyViewHolder(val listItemBinding: ListItemBinding): RecyclerView.ViewHolder(listItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding : ListItemBinding
        = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        context = parent.context
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.listItemBinding.apply {
            tvNumber.text = data[position].number
            tvName.text = data[position].name
            when(data[position].type){
                "صادرة" ->{
                    imageView.setImageResource(R.drawable.call_out)
                }
                "واردة" ->{
                    imageView.setImageResource(R.drawable.call_received)
                }
                "لم يتم الرد عليها" ->{
                    imageView.setImageResource(R.drawable.call_missed)
                }
            }
        }


            holder.listItemBinding.root.setOnLongClickListener {
                AlertDialog.Builder(context).apply {
                    setTitle("Delete alert")
                    setMessage("Deleted calls can't be restored.")
                    setPositiveButton("delete"){ dialogInterface: DialogInterface, i: Int ->
                        if(MySqliteDB(context).deleteCall(data[position].id)){
                            data.removeAt(position)
                            notifyDataSetChanged()
                        }

                    }
                    setCancelable(true)
                    setNegativeButton("no"){ dialogInterface: DialogInterface, _ ->
                        dialogInterface.cancel()
                    }
                    create()
                    show()
                }
                true
            }

    }

    override fun getItemCount(): Int {
        return data.size
    }

}