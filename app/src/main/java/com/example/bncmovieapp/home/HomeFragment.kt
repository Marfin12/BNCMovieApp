package com.example.bncmovieapp.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bncmovieapp.R
import com.example.bncmovieapp.core.data.Resource
import com.example.bncmovieapp.core.ui.MovieAdapter
import com.example.bncmovieapp.databinding.FragmentHomeBinding
import com.example.bncmovieapp.movie.MovieActivity
import com.example.bncmovieapp.movie.MovieActivity.Companion.MOVIE_ID
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val movieAdapter = MovieAdapter()
    private var maximum_page = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            movieAdapter.onItemClick = { selectedMovie ->
                val intent = Intent(activity, MovieActivity::class.java)

                MOVIE_ID = selectedMovie
                Timber.d("navigate to movie screen with movie id: ${selectedMovie.id}")

                intent.putExtra(MovieActivity.MOVIE_DATA, selectedMovie)
                startActivity(intent)
            }
            movieAdapter.onHeartClick = { selectedMovie ->
                homeViewModel.setFavoriteMovie(selectedMovie, !selectedMovie.isFavorite)
            }
            setupPagination()
            observeHomeViewModel()

            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun observeHomeViewModel() {
        homeViewModel.movie.observe(viewLifecycleOwner, { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> {
                        Timber.d("Fetching movie...")
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        Timber.d("Movie successfully loaded! ${movie.data}")
                        binding.progressBar.visibility = View.GONE
                        movieAdapter.setData(movie.data)
                        maximum_page = movie.data?.size!!
                    }
                    is Resource.Error -> {
                        Timber.d("Error when loading movie: ${movie.message}")
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text =
                            movie.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })
    }

    private fun setupPagination() {
        binding.rvMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && movieAdapter.maximum_data < maximum_page) {
                    binding.progressBarPagination.visibility = View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed({
                        binding.progressBarPagination.visibility = View.GONE
                        if (movieAdapter.maximum_data >= maximum_page) movieAdapter.maximum_data = maximum_page
                        else movieAdapter.maximum_data += 10
                        observeHomeViewModel()
                    }, 1000)
                }
            }
        })
    }


    override fun onDestroyView() {
        Timber.d("onDestroyView")
        super.onDestroyView()
        _binding = null
    }
}