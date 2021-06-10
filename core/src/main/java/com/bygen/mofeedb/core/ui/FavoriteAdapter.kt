package com.bygen.mofeedb.core.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bygen.mofeedb.core.R
import com.bygen.mofeedb.core.databinding.ItemsWithTagsBinding
import com.bygen.mofeedb.core.domain.model.MovieTv

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private var listFavorite = ArrayList<MovieTv>()
    var onItemClick: ((MovieTv) -> Unit)? = null

    fun setFavorite(videos: List<MovieTv>?) {
        if (videos == null) return
        this.listFavorite.clear()
        this.listFavorite.addAll(videos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemsWithTagsBinding =
            ItemsWithTagsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(itemsWithTagsBinding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favorite = listFavorite[position]
        holder.bind(favorite)
    }

    inner class FavoriteViewHolder(private val binding: ItemsWithTagsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(video: MovieTv) {
            with(binding) {
                tvItemTitle.text = video.title
                tvVote.text = video.vote.toString()
                tvRelease.text = video.release
                if (video.isMovie) {
                    tvTypeVideo.text = itemView.context.getString(R.string.movies)
                    tvTypeVideo.setBackgroundColor(Color.CYAN)
                } else {
                    tvTypeVideo.text = itemView.context.getString(R.string.tvshows)
                    tvTypeVideo.setBackgroundColor(Color.GREEN)

                }
                Glide.with(itemView.context)
                    .load(video.imageUrl)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }
        }
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listFavorite[bindingAdapterPosition])

            }
        }
    }


    override fun getItemCount(): Int = listFavorite.size


}