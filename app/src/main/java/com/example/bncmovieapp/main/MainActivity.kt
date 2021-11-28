package com.example.bncmovieapp.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bncmovieapp.R
import com.example.bncmovieapp.core.domain.model.Session
import com.example.bncmovieapp.favorite.FavoriteFragment
import com.example.bncmovieapp.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private val homeFragment = HomeFragment()
    private val favoriteFragment = FavoriteFragment()
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.d("Start debugging")

        setCurrentFragment(homeFragment)

        mainViewModel.sessionAdapter.observe(this, {selectedSession ->
            session = selectedSession
            if (session.token.isEmpty()) { navigateToLoginActivity() }
        })

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.favorite -> setCurrentFragment(favoriteFragment)
                R.id.home -> setCurrentFragment(homeFragment)
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_logout -> {
                mainViewModel.sessionAdapter.removeObservers(this)
                mainViewModel.endSession(session)
                Timber.d("Session end")

                navigateToLoginActivity()
            }
            else -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            Timber.d("Showing ${fragment}")
            replace(R.id.flFragment, fragment)
            commit()
        }

    private fun navigateToLoginActivity() {
        Timber.d("Navigate to login screen")
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}