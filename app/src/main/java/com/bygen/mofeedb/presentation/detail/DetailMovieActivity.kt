package com.bygen.mofeedb.presentation.detail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bygen.mofeedb.R
import com.bygen.mofeedb.core.data.source.Resource
import com.bygen.mofeedb.core.domain.model.MovieTv
import com.bygen.mofeedb.databinding.ActivityDetailMovieBinding
import com.bygen.mofeedb.databinding.ContentDetailMovieBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailMovieActivity : AppCompatActivity() {
    private lateinit var contentBinding: ContentDetailMovieBinding
    private lateinit var mainBinding: ActivityDetailMovieBinding

    private val viewModel: DetailMovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        contentBinding = mainBinding.detailContent
        setContentView(mainBinding.root)
        setSupportActionBar(mainBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        contentBinding.progressBar.visibility = View.VISIBLE


        val movie = intent.getParcelableExtra<MovieTv>(EXTRA_MOVIE)
        val television = intent.getParcelableExtra<MovieTv>(EXTRA_TELEVISION)
        if (movie?.isMovie == true) {
            viewModel.setSelectedMovie(movie.videoId, movie.fromSearch)
            viewModel.detailMovie.observe(this, { detailMovie ->
                if (detailMovie != null) {
                    populateMovieTv(detailMovie)
                }
            })
        } else {
            if (television != null) {
                viewModel.setSelectedTelevision(television.videoId, television.fromSearch)
            }
            viewModel.detailTelevision.observe(this, { detailTelevision ->
                if (detailTelevision != null) {
                    populateMovieTv(detailTelevision)
                }
            })

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                val intent = Intent(NavUtils.getParentActivityIntent(this))
                intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                startActivity(intent)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun populateMovieTv(detail: Resource<MovieTv>) {
        contentBinding.progressBar.visibility = View.GONE
        with(contentBinding) {
            when (detail) {
                is Resource.Loading -> contentBinding.progressBar.visibility = View.VISIBLE
                is Resource.Success -> {
                        contentBinding.progressBar.visibility = View.GONE
                        tvTitle.text = detail.data?.title
                        tvRelease.text = detail.data?.release
                        tvGenre.text = detail.data?.genre
                        tvTagline.text = detail.data?.tagline
                        when (detail.data?.duration) {
                            0 -> tvDuration.visibility = View.GONE
                            else -> tvDuration.text =
                                getString(
                                    R.string.duration_convert,
                                    detail.data?.duration.toString()
                                )
                        }
                        tvShowoverview.text = detail.data?.overview
                        Glide.with(this@DetailMovieActivity).load(detail.data?.imageUrl).apply(
                            RequestOptions.placeholderOf(
                                R.drawable.ic_loading
                            ).error(R.drawable.ic_error)
                        ).into(imagePoster)
                        tvVote.text = detail.data?.vote.toString()
                        val state = detail.data?.isFavorite
                    if (state != null) {
                        setFavoriteState(state)
                    }

                        fabFavorite.setOnClickListener {
                            if (detail.data?.isMovie == true) {
                                viewModel.setFavoriteMov()
                            } else {
                                viewModel.setFavoriteTv()
                            }
                            //state sebelum clicklistener favorite
                            if (!detail.data?.isFavorite!!) {
                                Toast.makeText(
                                    applicationContext,
                                    getString(R.string.favorited, detail.data?.title),
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    getString(R.string.unfavorite, detail.data?.title),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                }
                is Resource.Error -> {
                    contentBinding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun setFavoriteState(state: Boolean) {
        if (state) {
            contentBinding.fabFavorite.setImageResource(R.drawable.ic_favorite_red_24)
        } else {
            contentBinding.fabFavorite.setImageResource(R.drawable.ic_favorite_border_24_grey)
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TELEVISION = "extra_television"
    }
}
