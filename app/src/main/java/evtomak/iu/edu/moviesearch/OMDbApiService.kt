package evtomak.iu.edu.moviesearch

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDbApiService {
    @GET(".")
    fun searchMovie(
        @Query("apikey") apiKey: String,
        @Query("t") title: String
    ): Call<MovieResponse>
}

