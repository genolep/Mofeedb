package com.bygen.mofeedb.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bygen.mofeedb.core.R
import com.bygen.mofeedb.core.databinding.ItemsMovieBinding
import com.bygen.mofeedb.core.domain.model.MovieTv

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovie = ArrayList<MovieTv>()
    var onItemClick: ((MovieTv) -> Unit)? = null

    fun setMovie(movie: List<MovieTv>?) {
        if (movie == null) return
        this.listMovie.clear()
        this.listMovie.addAll(movie)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovieBinding =
            ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }

    inner class MovieViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieTv) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvVote.text = movie.vote.toString()
                tvRelease.text = movie.release

                Glide.with(itemView.context)
                    .load(movie.imageUrl)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }
        }
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listMovie[bindingAdapterPosition])

            }
        }
    }

    override fun getItemCount(): Int = listMovie.size
}