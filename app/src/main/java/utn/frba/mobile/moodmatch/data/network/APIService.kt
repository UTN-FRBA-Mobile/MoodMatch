package utn.frba.mobile.moodmatch.data.network

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import utn.frba.mobile.moodmatch.data.model.Activity
import utn.frba.mobile.moodmatch.data.model.Book
import utn.frba.mobile.moodmatch.data.model.LoginRequest
import utn.frba.mobile.moodmatch.data.model.LoginResponse
import utn.frba.mobile.moodmatch.data.model.Movie
import utn.frba.mobile.moodmatch.data.model.RecommendationResponse
import utn.frba.mobile.moodmatch.data.model.Series

interface APIService {

    @GET("api/recomendations")
    suspend fun getRecommendations(@Query("mood") mood: String): RecommendationResponse

    @GET("api/series")
    suspend fun getSeries(): List<Series>

    @GET("api/movies")
    suspend fun getMovies(): List<Movie>

    @GET("api/books")
    suspend fun getBooks(): List<Book>

    @GET("api/recreational-acitivies")
    suspend fun getRecreationalActivities(): List<Activity>

    @POST("api/user/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

}

object RetrofitServiceFactory {
    private const val BASE_URL = "https://moodmatch-back.onrender.com/"

    val api: APIService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }
}
