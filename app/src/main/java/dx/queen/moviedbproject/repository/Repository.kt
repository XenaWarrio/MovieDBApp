package dx.queen.moviedbproject.repository

import dx.queen.moviedbproject.data_source.INetworkDataSource
import dx.queen.moviedbproject.data_source.NetworkDataSource
import dx.queen.moviedbproject.data_source.retrofit

class Repository {
    private val networkDataSource =
        NetworkDataSource(
            retrofit.create(
                INetworkDataSource::class.java
            )
        )

    suspend fun getResponse() = networkDataSource.getMovies()
    suspend fun findMovies(title: String) = networkDataSource.findMovies(title)
    suspend fun getMovieDetail(movieId: Int) = networkDataSource.getMovieDetail(movieId)

}