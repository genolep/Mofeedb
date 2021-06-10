package com.bygen.mofeedb.presentation.popular.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bygen.mofeedb.core.data.source.Resource
import com.bygen.mofeedb.core.ui.TelevisionAdapter
import com.bygen.mofeedb.databinding.FragmentTelevisionBinding
import com.bygen.mofeedb.presentation.detail.DetailMovieActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class TelevisionFragment : Fragment() {
    private val viewModel: TelevisionViewModel by viewModel()

    private var _fragmentTelevisionBinding: FragmentTelevisionBinding? = null
    private val binding get() = _fragmentTelevisionBinding
    private lateinit var televisionAdapter: TelevisionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentTelevisionBinding =
            FragmentTelevisionBinding.inflate(layoutInflater, container, false)
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
        viewModel.television.observe(viewLifecycleOwner, { televisions ->
            if (televisions != null) {
                when (televisions) {
                    is Resource.Loading -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                        binding?.tvError?.visibility = View.GONE

                    }
                    is Resource.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.tvError?.visibility = View.GONE

                        televisionAdapter.setTelevision(televisions.data)
                    }
                    is Resource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.tvError?.text = televisions.message

                    }
                }
            }
        })
    }

    private fun setupRecycler() {
        televisionAdapter = TelevisionAdapter()
        televisionAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_TELEVISION, selectedData)
            startActivity(intent)
        }
        with(binding?.rvTelevision) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = televisionAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.rvTelevision?.adapter = null
        _fragmentTelevisionBinding = null

    }


}