package utn.frba.mobile.moodmatch.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import utn.frba.mobile.moodmatch.data.model.Activity
import utn.frba.mobile.moodmatch.data.model.Book
import utn.frba.mobile.moodmatch.data.model.Movie
import utn.frba.mobile.moodmatch.data.model.Recommendation
import utn.frba.mobile.moodmatch.data.model.Series

interface APIService {

    @GET("api/series")
    suspend fun getSeries(@Query("classification") classification: String): Recommendation<Series>

    @GET("api/movies")
    suspend fun getMovies(@Query("classification") classification: String): Recommendation<Movie>

    @GET("api/books")
    suspend fun getBooks(@Query("classification") classification: String): Recommendation<Book>

    @GET("api/recreational-acitivies")
    suspend fun getRecreationalActivities(@Query("classification") classification: String): Recommendation<Activity>

}

object RetrofitServiceFactory {
    private const val BASE_URL = "https://moodmatch-back.onrender.com/"

    val api: APIService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(APIService::class.java)
    }
}
