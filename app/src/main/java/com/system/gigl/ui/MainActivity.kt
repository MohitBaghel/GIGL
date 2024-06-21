package com.system.gigl.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.system.gigl.R
import com.system.gigl.repo.VideoRepository
import com.system.gigl.ui.adapter.ShortAdapter
import com.system.gigl.ui.adapter.VideoAdapter
import com.system.gigl.ui.viewmodel.MainViewModel
import com.system.gigl.ui.viewmodel.MainViewModelFactory
import com.system.gigl.utils.Result

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var videoAdapter: VideoAdapter
    private lateinit var shortAdapter: ShortAdapter
    private lateinit var progressBar: ProgressBar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        progressBar = findViewById(R.id.progressBar)
        val videoRepository = VideoRepository(this)
        val viewModelFactory = MainViewModelFactory(videoRepository)
        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        setupRecyclerViews()
        observeViewModel()

        mainViewModel.fetchVideos()
        mainViewModel.fetchShorts()
    }

    private fun setupRecyclerViews() {
        videoAdapter = VideoAdapter()
        val rvVideos: RecyclerView = findViewById(R.id.rvVideos)
        rvVideos.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = videoAdapter
        }

        shortAdapter = ShortAdapter()
        val rvShorts: RecyclerView = findViewById(R.id.rvShorts)
        rvShorts.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = shortAdapter
        }
    }

    private fun observeViewModel() {
        mainViewModel.videos.observe(this, Observer { resource ->
            when (resource) {
                is Result.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    progressBar.visibility = View.GONE
                    resource.data?.let {
                        videoAdapter.setVideos(it)
                    }
                }
                is Result.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show()
                }

                is Result.Error -> TODO()
                Result.Loading -> TODO()
            }
        })

        mainViewModel.shorts.observe(this, Observer { resource ->
            when (resource) {
                is Result.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    progressBar.visibility = View.GONE
                    resource.data?.let {
                        shortAdapter.setShorts(it)
                    }
                }
                is Result.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show()
                }


            }
        })

    }
}
