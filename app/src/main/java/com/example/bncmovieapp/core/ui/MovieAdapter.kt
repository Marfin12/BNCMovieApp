package com.example.bncmovieapp.core.ui

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bncmovieapp.R
import com.example.bncmovieapp.core.domain.model.Movie
import com.example.bncmovieapp.databinding.ItemListMovieBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var listData = ArrayList<Movie>()
    var maximum_data = 10
    var onItemClick: ((Movie) -> Unit)? = null
    var onHeartClick: ((Movie) -> Unit)? = null

    @RequiresApi(Build.VERSION_CODES.N)
    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        listData.removeIf { it.id.toInt() > maximum_data }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMovieBinding.bind(itemView)
        fun bind(data: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.imageUrl)
                    .into(ivItemImage)
                tvItemTitle.text = data.title

                if (data.isFavorite) ivFavoriteImage.setImageResource(R.drawable.ic_favorite_foreground)
                else ivFavoriteImage.setImageResource(R.drawable.ic_unfavorite_foreground)

                ivFavoriteImage.setOnClickListener {
                    onHeartClick?.invoke(listData[adapterPosition])
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}