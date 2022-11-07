package com.example.newnewsbyKunalKumar

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener: NewsItemListener): RecyclerView.Adapter<NewsViewHolder>() {
    val item:ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_list,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(item[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentView = item[position]
        holder.titleView.text = currentView.title
        holder.authorView.text = currentView.author
        Glide.with(holder.itemView.context).load(currentView.urlToImage).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return item.size
    }
    fun update(updatedNews: ArrayList<News>){
        item.clear()
        item.addAll(updatedNews)
        notifyDataSetChanged()
    }
}
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val titleView:TextView = itemView.findViewById(R.id.textView2)
    val imageView:ImageView = itemView.findViewById(R.id.imageView)
    val authorView:TextView = itemView.findViewById(R.id.textView)
}
interface NewsItemListener{
    fun onItemClicked(item : News)
}