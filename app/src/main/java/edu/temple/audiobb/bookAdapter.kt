package edu.temple.audiobb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class bookAdapter (val title: Array<String>, val author: Array<String>,
                   val clickListener: ClickListener):
    RecyclerView.Adapter<bookAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bookAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.placeholder, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return title.size
    }

    class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var titleTextView: TextView
        var authorTextView: TextView
        init{
            titleTextView = view.findViewById(R.id.titleView)
            authorTextView = view.findViewById(R.id.authorView)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titleTextView.text=title.get(position)
        holder.itemView.setOnClickListener{
            clickListener.onItemClick(title.get(position))
        }
        holder.authorTextView.text=author.get(position)
        holder.itemView.setOnClickListener{
            clickListener.onItemClick(author.get(position))
        }

    }

    interface ClickListener{
        fun onItemClick(string: String)
    }

}