package com.bygen.mofeedb.presentation.popular.mov

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bygen.mofeedb.core.data.source.Resource
import com.bygen.mofeedb.core.ui.MovieAdapter
import com.bygen.mofeedb.databinding.FragmentMovieBinding
import com.bygen.mofeedb.presentation.detail.DetailMovieActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieFragment : Fragment() {
    private val viewModel: MovieViewModel by viewModel()

    private var _fragmentMovieBinding: FragmentMovieBinding? = null
    private val binding get() = _fragmentMovieBinding!!
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            setupRecycler()
            setupViewModel()
        }
    }

    private fun setupViewModel() {
        viewModel.movie.observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.tvError.visibility = View.GONE

                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvError.visibility = View.GONE

                        movieAdapter.setMovie(movies.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvError.text = movies.message
                    }
                }
            }
        })
    }

    private fun setupRecycler() {
        movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, selectedData)
            startActivity(intent)
        }
        with(binding.rvMovie) {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
            this.adapter = movieAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentMovieBinding = null
    }

}