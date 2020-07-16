package dx.queen.moviedbproject.models.real_models

import com.squareup.moshi.Json

data class ResultResponse(
    @field:Json(name = "page") val page: Int,
    @field:Json(name = "results") val results: List<Movie>? = null,
    @field:Json(name = "total_pages") val totalPages: Int,
    @field:Json(name = "total_results") val totalResults: Int
)

data class Movie(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "original_title") val title: String,
    @field:Json(name = "overview") val overview: String,
    @field:Json(name = "poster_path") val poster: String

)

data class MovieDetail(
    @field:Json(name = "backdrop_path") val backdrop_path: String? = null,
    @field:Json(name = "budget") val budget: Int,
    @field:Json(name = "genres") val genres: List<Genres>,
    @field:Json(name = "overview") val overview: String? = null,
    @field:Json(name = "release_date") val release_date: String,
    @field:Json(name = "title") val title: String

)

data class Genres(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String
)