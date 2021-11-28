package com.example.bncmovieapp.movie

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bncmovieapp.BuildConfig
import com.example.bncmovieapp.R
import com.example.bncmovieapp.core.data.Resource
import com.example.bncmovieapp.core.domain.model.Movie
import com.example.bncmovieapp.databinding.ActivityMovieBinding
import kotlinx.android.synthetic.main.content_picture_movie.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class MovieActivity : AppCompatActivity() {

    companion object {
        const val MOVIE_DATA = "movie_data"
        var MOVIE_ID = Movie(
            "",
            "",
            "",
            "",
            "",
            false,
            "",
            arrayListOf(),
            "",
            ""
        )
    }

    private val movieViewModel: MovieViewModel by viewModel()
    private lateinit var binding: ActivityMovieBinding
    private lateinit var movieData: Movie
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (BuildConfig.DEBUG && supportActionBar == null) {
            Timber.d("Assertion failed")
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        movieData = intent.getParcelableExtra(MOVIE_DATA)!!
        isFavorite = movieData.isFavorite

        if (MOVIE_ID.id.isNotEmpty()) {
            movieViewModel.movie.observe(this, { movie ->
                if (movie != null) {
                    when (movie) {
                        is Resource.Loading -> {
                            Timber.d("Fetching movie...")
                            binding.progressBar.visibility = View.VISIBLE
                            binding.incMovieImage.root.visibility = View.GONE
                            binding.incMovieContent.root.visibility = View.GONE
                        }
                        is Resource.Success -> {
                            Timber.d("Movie successfully loaded! ${movie.data}")
                            binding.progressBar.visibility = View.GONE
                            binding.incMovieImage.root.visibility = View.VISIBLE
                            binding.incMovieContent.root.visibility = View.VISIBLE

                            movie.data?.let { mapMovieDetailToUI(it) }
                            mapFavoriteToUI()
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
        } else mapFavoriteToUI()
    }

    private fun mapMovieDetailToUI(movie: Movie) {
        Glide.with(this)
            .load(movie.imageUrl)
            .into(iv_movie)
        binding.incMovieContent.tvTitle.text = "Title of movie  ${movie.id}. ${movie.title}"
        binding.incMovieContent.tvGenre.text = "${movie.genre} - ${movie.duration}"
        binding.incMovieContent.tvContent.text = movie.desc

        mapStarringToActor(movie.starring)
        mapRatingToStar(movie.rating.toInt())
    }

    private fun mapFavoriteToUI() {
        with(binding.incMovieImage) {
            setStatusFavorite()
            ivFavoriteImage.setOnClickListener {
                isFavorite = !isFavorite
                setStatusFavorite()
                movieViewModel.setFavoriteMovie(movieData, isFavorite)
            }
        }
    }

    private fun mapStarringToActor(starring: ArrayList<String>) {
        var actors = ""

        starring.forEach { actor ->
            actors += actor + "\n"
        }
        binding.incMovieContent.tvActorContent.text = actors
    }

    private fun mapRatingToStar(rating: Int) {
        if (rating >= 1) binding.incMovieContent.ivStarImage1.setImageResource(R.drawable.ic_rated_foreground)
        if (rating >= 2) binding.incMovieContent.ivStarImage2.setImageResource(R.drawable.ic_rated_foreground)
        if (rating >= 3) binding.incMovieContent.ivStarImage3.setImageResource(R.drawable.ic_rated_foreground)
        if (rating >= 4) binding.incMovieContent.ivStarImage4.setImageResource(R.drawable.ic_rated_foreground)
        if (rating >= 5) binding.incMovieContent.ivStarImage5.setImageResource(R.drawable.ic_rated_foreground)
    }

    private fun setStatusFavorite() {
        with(binding.incMovieImage) {
            if (isFavorite)
                ivFavoriteImage.setImageResource(R.drawable.ic_favorite_foreground)
            else ivFavoriteImage.setImageResource(R.drawable.ic_unfavorite_foreground)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}