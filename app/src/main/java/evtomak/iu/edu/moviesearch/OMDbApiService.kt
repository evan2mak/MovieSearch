package evtomak.iu.edu.moviesearch

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// Interface that defines a method searchMovie for making a GET request to the OMDb API to retrieve movie details by title using Retrofit
interface OMDbApiService {
    @GET(".")
    fun searchMovie(
        @Query("apikey") apiKey: String,
        @Query("t") title: String
    ): Call<MovieResponse>
}

