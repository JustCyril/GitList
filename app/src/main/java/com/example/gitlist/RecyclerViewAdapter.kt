package com.example.gitlist

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View
import android.view.ViewGroup;
import android.widget.TextView

class RecyclerViewAdapter(private var postItems: ArrayList<PostItem>/*, private var onItemClickListener: (PostItem) -> Unit*/) : RecyclerView.Adapter<RecyclerViewAdapter.BaseViewHolder>() {

    class BaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvDate = itemView.findViewById(R.id.textViewDate) as TextView
        var tvTitle = itemView.findViewById(R.id.textViewTitle) as TextView
        var tvDescription = itemView.findViewById(R.id.textViewDescription) as TextView

    }

/*    private val VIEW_TYPE_LOADING = 0
    private val VIEW_TYPE_NORMAL = 1
    private val isLoaderVisible = false*/

    override fun getItemCount() = postItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

/*        when (viewType) {
            VIEW_TYPE_NORMAL -> return BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent,false))
            VIEW_TYPE_LOADING -> return
        }*/

        return BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent,false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        holder.tvDate.text = postItems[position].time
        holder.tvDescription.text = postItems[position].description
        holder.tvTitle.text = postItems[position].title

        holder.itemView.setOnClickListener {
            //onItemClickListener(postItems[position])
        }
    }

    fun addData(listItems: ArrayList<PostItem>) {
        val size = listItems.size
        postItems.addAll(listItems)
        val sizeNew = postItems.size
        notifyItemRangeChanged(size, sizeNew)
    }

    fun clear() {
        postItems.clear()
    }

}