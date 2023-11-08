package evtomak.iu.edu.moviesearch

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

// MainActivity handles UI interactions and movie search functionality
class MainActivity : AppCompatActivity() {
    private lateinit var retrofit: Retrofit
    private lateinit var omDbApiService: OMDbApiService
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar)

        retrofit = RetrofitClient.retrofit
        omDbApiService = retrofit.create(OMDbApiService::class.java)

        recyclerView = findViewById(R.id.recyclerViewMovies)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val buttonSearch: Button = findViewById(R.id.buttonSearch)
        buttonSearch.setOnClickListener {
            val editTextMovieTitle: EditText = findViewById(R.id.editTextMovieTitle)
            val title = editTextMovieTitle.text.toString().trim()
            if (title.isEmpty()) {
                Toast.makeText(this, "Empty search. Type in a movie title.", Toast.LENGTH_LONG).show()
            }
            else {
                searchMovie(title)
            }
        }
    }

    // Search for a movie using the OMDb API
    private fun searchMovie(title: String) {
        omDbApiService.searchMovies("b108cd00", title).enqueue(object :
            Callback<MovieListResponse> {
            override fun onResponse(
                call: Call<MovieListResponse>,
                response: Response<MovieListResponse>
            ) {
                val moviesResponse = response.body()
                if (moviesResponse != null && response.isSuccessful && moviesResponse.response != "False") {
                    movieAdapter = MovieAdapter(moviesResponse.search ?: listOf())
                    recyclerView.adapter = movieAdapter

                    // Fetch details for each movie
                    for (movie in moviesResponse.search ?: emptyList()) {
                        fetchMovieDetails(movie.imdbID ?: "")
                    }
                }
                else {
                    val errorMessage =
                        moviesResponse?.error ?: "No movies found or error in fetching data."
                    Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
    // Inflate the menu; this adds items to the action bar if it is present
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // Handles action bar item clicks for feedback
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_feedback -> {
                sendFeedback()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Opens email client to send feedback
    private fun sendFeedback() {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("evtomak@iu.edu"))
            putExtra(Intent.EXTRA_SUBJECT, "Feedback")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intent, "Choose an email client:"))
        }
        else {
            Toast.makeText(this, "No email clients installed.", Toast.LENGTH_SHORT).show()
        }
    }

    // This method is for fetching detailed information about each movie
    private fun fetchMovieDetails(movieId: String) {
        omDbApiService.getMovieDetails("b108cd00", movieId).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movieDetail = response.body()
                if (movieDetail != null) {
                    // Update the movie details in the adapter
                    movieAdapter.updateMovieDetails(movieId, movieDetail)
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // Handle the failure case
                Toast.makeText(this@MainActivity, "Failed to fetch movie details for $movieId", Toast.LENGTH_LONG).show()
            }
        })
    }
}

