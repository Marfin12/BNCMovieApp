package com.example.bncmovieapp.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.bncmovieapp.core.data.Resource
import com.example.bncmovieapp.databinding.ActivityLoginBinding
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        mainViewModel.initLoginAdapter()
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isEmpty()) {
                binding.etEmail.error = "Username is not entered"
                binding.etEmail.requestFocus()
            } else if (password.isEmpty()) {
                binding.etPassword.error = "Username is not entered"
                binding.etPassword.requestFocus()
            } else {
                mainViewModel.generateLoginAdapter(email, password)
                mainViewModel.loginAdapter[0].observe(this, { token ->
                    if (token != null) {
                        when (token) {
                            is Resource.Loading -> {
                                Timber.d("Fetching...")
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            is Resource.Success -> {
                                Timber.d("Successfully login")
                                binding.progressBar.visibility = View.GONE
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            }
                            is Resource.Error -> {
                                binding.progressBar.visibility = View.GONE
                                Timber.d("Error when login: ${token.message}")
                            }
                        }
                    }
                })
            }
        }
        setContentView(binding.root)
    }
}