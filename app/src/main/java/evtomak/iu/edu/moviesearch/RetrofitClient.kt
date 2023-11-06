package evtomak.iu.edu.moviesearch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Singleton that initializes a Retrofit instance
object RetrofitClient {
    private const val BASE_URL = "https://www.omdbapi.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
