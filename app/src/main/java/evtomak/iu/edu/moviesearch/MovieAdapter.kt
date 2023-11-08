package evtomak.iu.edu.moviesearch

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

// A RecyclerView Adapter that binds movie data to a list and handles view creation and binding for a movie list UI
class MovieAdapter(private var movies: List<MovieResponse>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val movieIds: List<String> = movies.mapNotNull { it.imdbID }

    // Inflates the movie item layout and returns a new view holder for the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    // Binds movie data to the view holder at the specified position in the RecyclerView
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)


    }

    // Returns the total number of items in the movie list data set.
    override fun getItemCount() = movies.size

    // ViewHolder class that contains and binds views for individual movie items in the RecyclerView
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewPoster: ImageView = itemView.findViewById(R.id.imageViewPoster)
        private val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        private val textViewImdbLink: TextView = itemView.findViewById(R.id.textViewImdbLink)
        private val buttonShare: Button = itemView.findViewById(R.id.buttonShare)
        private val textViewYear: TextView = itemView.findViewById(R.id.textViewYear)
        private val textViewRating: TextView = itemView.findViewById(R.id.textViewRating)
        private val textViewRuntime: TextView = itemView.findViewById(R.id.textViewRuntime)
        private val textViewGenre: TextView = itemView.findViewById(R.id.textViewGenre)
        private val textViewIMDbRating: TextView = itemView.findViewById(R.id.textViewIMDbRating)

        // Binds movie details to the views and sets click listeners for actions such as viewing on IMDb or sharing
        @SuppressLint("SetTextI18n")
        fun bind(movie: MovieResponse) {
            Glide.with(itemView.context).load(movie.poster).into(imageViewPoster)
            textViewTitle.text = "Title: ${movie.title}"
            textViewYear.text = "Year: ${movie.year}"
            textViewRating.text = "Rating: ${movie.rated ?: "N/A"}"
            textViewRuntime.text = "Runtime: ${movie.runtime ?: "N/A"}"
            textViewGenre.text = "Genre: ${movie.genre ?: "N/A"}"
            textViewIMDbRating.text = "IMDb Rating: ${movie.imdbRating ?: "N/A"}"

            val imdbUrl = "https://www.imdb.com/title/${movie.imdbID}"
            textViewImdbLink.apply {
                text = "View on IMDb"  // Set the display text to "View on IMDb"
                setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(imdbUrl))
                    itemView.context.startActivity(intent)
                }
            }

            buttonShare.setOnClickListener {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "${movie.title} - $imdbUrl")
                    type = "text/plain"
                }
                itemView.context.startActivity(Intent.createChooser(shareIntent, "Share via"))
            }
        }
    }

    // Update individual movie details
    fun updateMovieDetails(imdbID: String, details: MovieResponse) {
        val updatedMovies = movies.toMutableList()
        val position = updatedMovies.indexOfFirst { it.imdbID == imdbID }
        if (position != -1) {
            updatedMovies[position] = details
            movies = updatedMovies
            notifyItemChanged(position)
        }
    }
}
