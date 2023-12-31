package evtomak.iu.edu.moviesearch

import com.google.gson.annotations.SerializedName

// Data class representing a movie's details along with the API response status and any error messages received from the OMDb API
data class MovieResponse(
    @SerializedName("Title") val title: String?,
    @SerializedName("Year") val year: String?,
    @SerializedName("Rated") val rated: String?,
    @SerializedName("Runtime") val runtime: String?,
    @SerializedName("Genre") val genre: String?,
    @SerializedName("Poster") val poster: String?,
    @SerializedName("imdbRating") val imdbRating: String?,
    @SerializedName("imdbID") val imdbID: String?,
    val response: String, // This will capture "True" or "False"
    val error: String? // This will capture the error message if "Response" is "False"
)
