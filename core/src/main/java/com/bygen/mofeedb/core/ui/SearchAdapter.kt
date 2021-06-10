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

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private var listSearch = ArrayList<MovieTv>()
    var onItemClick: ((MovieTv) -> Unit)? = null

    fun setSearch(searches: List<MovieTv>?) {
        if (searches == null) return
        this.listSearch.clear()
        this.listSearch.addAll(searches)
        notifyDataSetChanged()
    }

    inner class SearchViewHolder(private val binding: ItemsWithTagsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(search: MovieTv) {
            with(binding) {
                tvItemTitle.text = search.title
                tvVote.text = search.vote.toString()
                tvRelease.text = search.release
                if (search.isMovie) {
                    tvTypeVideo.text = itemView.context.getString(R.string.movies)
                    tvTypeVideo.setBackgroundColor(Color.CYAN)
                } else {
                    tvTypeVideo.text = itemView.context.getString(R.string.tvshows)
                    tvTypeVideo.setBackgroundColor(Color.GREEN)

                }

                Glide.with(itemView.context)
                    .load(search.imageUrl)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }

        }
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listSearch[bindingAdapterPosition])

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.SearchViewHolder {
        val itemsWithTagsBinding =
            ItemsWithTagsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(itemsWithTagsBinding)
    }

    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) {
        val search = listSearch[position]
        holder.bind(search)
    }

    override fun getItemCount(): Int = listSearch.size
}