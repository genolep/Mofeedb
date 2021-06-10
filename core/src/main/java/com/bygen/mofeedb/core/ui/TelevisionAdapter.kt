package com.bygen.mofeedb.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bygen.mofeedb.core.R
import com.bygen.mofeedb.core.databinding.ItemsMovieBinding
import com.bygen.mofeedb.core.domain.model.MovieTv

class TelevisionAdapter : RecyclerView.Adapter<TelevisionAdapter.TelevisionViewHolder>() {

    private var listTv = ArrayList<MovieTv>()
    var onItemClick: ((MovieTv) -> Unit)? = null
    fun setTelevision(tv: List<MovieTv>?) {
        if (tv == null) return
        this.listTv.clear()
        this.listTv.addAll(tv)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TelevisionViewHolder {
        val itemsAcademyBinding =
            ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TelevisionViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: TelevisionViewHolder, position: Int) {
        val television = listTv[position]
        holder.bind(television)
    }


    inner class TelevisionViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(television: MovieTv) {
            with(binding) {
                tvItemTitle.text = television.title
                tvRelease.text = television.release
                tvVote.text = television.vote.toString()
                Glide.with(itemView.context)
                    .load(television.imageUrl)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }
        }
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listTv[bindingAdapterPosition])

            }
        }
    }

    override fun getItemCount(): Int = listTv.size
}