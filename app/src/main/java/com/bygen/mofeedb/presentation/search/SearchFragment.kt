package com.bygen.mofeedb.presentation.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bygen.mofeedb.R
import com.bygen.mofeedb.core.data.source.Resource
import com.bygen.mofeedb.core.ui.SearchAdapter
import com.bygen.mofeedb.databinding.FragmentSearchBinding
import com.bygen.mofeedb.presentation.detail.DetailMovieActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private var _fragmentSearchBinding: FragmentSearchBinding? = null
    private val binding get() = _fragmentSearchBinding
    private val searchViewModel: SearchViewModel by viewModel()
    private lateinit var searchAdapter: SearchAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentSearchBinding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            searchManage()
            setupRecycler()
            setupViewModel()
        }

    }

    private fun setupViewModel() {
        searchViewModel.search.observe(viewLifecycleOwner, { searchResult ->
            if (searchResult != null) {
                when (searchResult) {
                    is Resource.Loading -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                        binding?.emptySearch?.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.emptySearch?.visibility = View.GONE
                        searchAdapter.setSearch(searchResult.data)
                    }
                    is Resource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.emptySearch?.visibility = View.VISIBLE
                        binding?.emptySearch?.text = searchResult.message
                    }
                }
            }
            if(searchResult.data?.isEmpty() == true){
                binding?.emptySearch?.visibility = View.VISIBLE
                binding?.emptySearch?.text = activity?.getString(R.string.empty_search)
            }

        })
    }

    private fun setupRecycler() {
        searchAdapter = SearchAdapter()
        searchAdapter.onItemClick = { selectedData ->
            val intent = Intent(
                activity,
                DetailMovieActivity::class.java
            )
            if (selectedData.isMovie) intent.putExtra(
                DetailMovieActivity.EXTRA_MOVIE,
                selectedData
            ) else intent.putExtra(DetailMovieActivity.EXTRA_TELEVISION, selectedData)
            startActivity(intent)
        }
        with(binding?.rvSearch) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = searchAdapter
        }
    }

    private fun searchManage() {
        binding?.search?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                binding?.progressBar?.visibility = View.VISIBLE
                searchViewModel.setSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.rvSearch?.adapter = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentSearchBinding = null
    }

}