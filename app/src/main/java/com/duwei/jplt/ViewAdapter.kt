package com.duwei.jplt

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view.view.*

class ViewAdapter(private val vocabs : List<ViewData>) : RecyclerView.Adapter<ViewAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewAdapter.MyViewHolder, position: Int) {
        val currentItem = vocabs[position]

        holder.vocab.text = currentItem.vocab
        holder.times.text = currentItem.times
        holder.kanji.text = currentItem.kanji
        holder.type.text = currentItem.type
        holder.meaning = currentItem.meaning
    }

    override fun getItemCount()=vocabs.size

    class MyViewHolder (v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        var vocab:TextView=v.vocab
        var times:TextView=v.times
        var kanji:TextView=v.kanJi
        var type:TextView=v.type
        var meaning:String=""

        init{
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Log.d("myDebug", "Clicked")
            val text = meaning
            val duration = Toast.LENGTH_SHORT
            if (v != null) {
                Toast.makeText(v.context,text,duration).show()
            }
        }

    }

}
