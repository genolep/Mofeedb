package com.bygen.mofeedb.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bygen.mofeedb.core.ui.FavoriteAdapter
import com.bygen.mofeedb.databinding.FragmentFavoriteMovieBinding
import com.bygen.mofeedb.di.favoriteModule
import com.bygen.mofeedb.presentation.detail.DetailMovieActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class FavoriteFragment : Fragment() {
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private var _fragmentMovieBinding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _fragmentMovieBinding
    private lateinit var videoAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadKoinModules(favoriteModule)
        _fragmentMovieBinding =
            FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            setupRecycler()
            setupViewModel()

        }
    }

    private fun setupViewModel() {
        favoriteViewModel.favorite.observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                binding?.progressBar?.visibility = View.GONE
                binding?.emptyMovie?.visibility = View.GONE
                videoAdapter.setFavorite(movies)

            }
            if (movies.isEmpty()) {
                binding?.progressBar?.visibility = View.GONE
                binding?.emptyMovie?.visibility = View.VISIBLE
            }
        })
        with(binding?.rvMovie) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = videoAdapter
        }
    }

    private fun setupRecycler() {
        videoAdapter = FavoriteAdapter()
        videoAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailMovieActivity::class.java)
            if (selectedData.isMovie) intent.putExtra(
                DetailMovieActivity.EXTRA_MOVIE,
                selectedData
            ) else intent.putExtra(DetailMovieActivity.EXTRA_TELEVISION, selectedData)
            startActivity(intent)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding?.rvMovie?.adapter = null
    }


    override fun onDestroy() {
        super.onDestroy()
        _fragmentMovieBinding = null
        unloadKoinModules(favoriteModule)
    }

}