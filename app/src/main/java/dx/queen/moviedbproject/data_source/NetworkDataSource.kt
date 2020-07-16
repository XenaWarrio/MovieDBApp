package dx.queen.moviedbproject.data_source

import dx.queen.moviedbproject.models.real_models.MovieDetail
import dx.queen.moviedbproject.models.real_models.ResultResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

val retrofit: Retrofit by lazy {

    Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

}


interface INetworkDataSource {

    @GET("trending/movie/week")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): ResultResponse

    @GET("search/movie")
    suspend fun findMovies(
        @Query("api_key") apiKey: String, @Query("query") title: String
    ): ResultResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int, @Query("api_key") apiKey: String
    ): MovieDetail


}


class NetworkDataSource(private val retrofit: INetworkDataSource) {

    private val apiKey = "8d96120d087a1a7cf0db4af21ce925a6"

    suspend fun getMovies() = retrofit.getPopularMovies(apiKey)
    suspend fun findMovies(title: String) = retrofit.findMovies(apiKey, title)
    suspend fun getMovieDetail(movie_id: Int) = retrofit.getMovieDetail(movie_id, apiKey)

}