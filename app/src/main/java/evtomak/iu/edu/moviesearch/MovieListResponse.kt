package evtomak.iu.edu.moviesearch

import com.google.gson.annotations.SerializedName

// A data class representing the response from a movie search query, including a list of movies, total results, and status of the response.
data class MovieListResponse(
    @SerializedName("Search") val search: List<MovieResponse>?,
    @SerializedName("totalResults") val totalResults: String?,
    @SerializedName("Response") val response: String,
    val error: String? // This will capture the error message if "Response" is "False"
)
