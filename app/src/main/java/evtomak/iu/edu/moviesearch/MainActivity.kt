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
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private lateinit var retrofit: Retrofit
    private lateinit var omDbApiService: OMDbApiService
    private var currentMovie: MovieResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar)

        // Initialize Retrofit
        retrofit = RetrofitClient.retrofit
        omDbApiService = retrofit.create(OMDbApiService::class.java)

        val buttonSearch: Button = findViewById(R.id.buttonSearch)
        buttonSearch.setOnClickListener {
            val editTextMovieTitle: EditText = findViewById(R.id.editTextMovieTitle)
            val title = editTextMovieTitle.text.toString().trim()
            if (title.isEmpty()) {
                Toast.makeText(this@MainActivity, "Empty search. Type in a movie title.", Toast.LENGTH_LONG).show()
            }
            else {
                searchMovie(title)
            }
        }

        // Initialize IMDb link and share button but with no setOnClickListener
        val textViewImdbLink: TextView = findViewById(R.id.textViewImdbLink)
        val buttonShare: Button = findViewById(R.id.buttonShare)

        textViewImdbLink.setOnClickListener {
            currentMovie?.let {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.imdb.com/title/${it.imdbID}"))
                startActivity(intent)
            } ?: Toast.makeText(this@MainActivity, "No movie to view on IMDb.", Toast.LENGTH_LONG).show()
        }

        buttonShare.setOnClickListener {
            currentMovie?.let {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "${it.title} - https://www.imdb.com/title/${it.imdbID}")
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, "Share via"))
            } ?: Toast.makeText(this@MainActivity, "Nothing to share. Search for an existing movie first.", Toast.LENGTH_LONG).show()
        }
    }

    private fun searchMovie(title: String) {
        omDbApiService.searchMovie("b108cd00", title).enqueue(object :
            Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movieResponse = response.body()
                // Check if the response body is not null and if the response from the OMDb API indicates success
                if (movieResponse != null && response.isSuccessful && movieResponse.response != "False") {
                    updateUI(movieResponse)
                    if(currentMovie?.title == null){
                        val errorMessage = movieResponse?.error ?: "No movie found or error in fetching data."
                        Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_LONG).show()
                        clearUI()
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }


    @SuppressLint("SetTextI18n")
    private fun updateUI(movie: MovieResponse) {
        currentMovie = movie
        // Get references to the UI elements
        val imageViewPoster: ImageView = findViewById(R.id.imageViewPoster)
        val textViewTitle: TextView = findViewById(R.id.textViewTitle)
        val textViewYear: TextView = findViewById(R.id.textViewYear)
        val textViewRating: TextView = findViewById(R.id.textViewRating)
        val textViewRuntime: TextView = findViewById(R.id.textViewRuntime)
        val textViewGenre: TextView = findViewById(R.id.textViewGenre)
        val textViewIMDbRating: TextView = findViewById(R.id.textViewIMDbRating)

        // Set the data to the views
        Glide.with(this).load(movie.poster).into(imageViewPoster)
        textViewTitle.text = "Title: ${movie.title}"
        textViewYear.text = "Year: ${movie.year}"
        textViewRating.text = "Rated: ${movie.rated}"
        textViewRuntime.text = "Runtime: ${movie.runtime}"
        textViewGenre.text = "Genre: ${movie.genre}"
        textViewIMDbRating.text = "IMDb Rating: ${movie.imdbRating}"
    }

    @SuppressLint("SetTextI18n")
    private fun clearUI() {
        currentMovie = null
        val imageViewPoster: ImageView = findViewById(R.id.imageViewPoster)
        val textViewTitle: TextView = findViewById(R.id.textViewTitle)
        val textViewYear: TextView = findViewById(R.id.textViewYear)
        val textViewRating: TextView = findViewById(R.id.textViewRating)
        val textViewRuntime: TextView = findViewById(R.id.textViewRuntime)
        val textViewGenre: TextView = findViewById(R.id.textViewGenre)
        val textViewIMDbRating: TextView = findViewById(R.id.textViewIMDbRating)

        // Clear the data from the views
        imageViewPoster.setImageDrawable(null) // This will remove the current image from the ImageView
        textViewTitle.text = ""
        textViewYear.text = ""
        textViewRating.text = ""
        textViewRuntime.text = ""
        textViewGenre.text = ""
        textViewIMDbRating.text = ""
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_feedback -> {
                sendFeedback()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

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
}

