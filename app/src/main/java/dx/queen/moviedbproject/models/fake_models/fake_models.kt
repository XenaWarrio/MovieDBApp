package dx.queen.moviedbapplication.fake_models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieFakeItem(val poster: String, val name: String, val description: String) : Parcelable
data class MovieFakeDetail(
    val poster: String,
    val name: String,
    val fullDescription: String,
    val releaseDate: String,
    val directors: String,
    val cast: String,
    val genres: String
)

internal fun getMovieFakeItemsList(): List<MovieFakeItem> {
    val movie = MovieFakeItem(
        "C:\\AndroidProjects\\MovieDBApplication\\app\\src\\main\\res\\drawable-v24\\ic_launcher_foreground.xml",
        "Android",
        " This is movie about android and beautiful girl with a beautiful name Ksenia." +
                " One day she started learn programming and fell in love with mobile development. "
    )
    val list = mutableListOf<MovieFakeItem>()

    for (i in 0..20) {
        list.add(i, movie)
    }
    return list.toList()
}

internal fun getMovieFakeDetailItem(): MovieFakeDetail {
    return MovieFakeDetail(
        "C:\\AndroidProjects\\MovieDBApplication\\app\\src\\main\\res\\drawable-v24\\ic_launcher_foreground.xml",
        "Android",
        " This is movie about android and beautiful girl with a beautiful name Ksenia." +
                " One day she started learn programming and fell in love with mobile development. " +
                "But a thousand Android robots attacked the world. Therefore, Ksenia had to take the fate of the world into her beautiful hands." +
                " The film is incredible with its ending and the main characters.",
        "24/09/2020",
        "Ksenia, Ksenia, Ksenia",
        "10000000000000000$",
        "Comedy, Thriller, Action "

    )
}