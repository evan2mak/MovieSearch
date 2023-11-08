package evtomak.iu.edu.moviesearch

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// Interface that defines methods for making a GET request to the OMDb API to retrieve movie details by title using Retrofit
interface OMDbApiService {
    @GET(".")
    fun searchMovies(
        @Query("apikey") apiKey: String,
        @Query("s") search: String
    ): Call<MovieListResponse>

    @GET(".")
    fun getMovieDetails(
        @Query("apikey") apiKey: String,
        @Query("i") imdbId: String
    ): Call<MovieResponse>
}
