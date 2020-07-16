package dx.queen.moviedbproject.convertImage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

 fun getMoviePosterUrl(moviePoster: String): String {
    return if (moviePoster.startsWith("http")) {
        moviePoster
    } else {
        "http://image.tmdb.org/t/p/w300$moviePoster"
    }
}


 fun getBitmapFromURL(src: String?): Bitmap? {
    return try {
        val url = URL(src)
        val connection: HttpURLConnection = url
            .openConnection() as HttpURLConnection
        connection.setDoInput(true)
        connection.connect()
        val input: InputStream = connection.getInputStream()
        BitmapFactory.decodeStream(input)
    } catch (e: IOException) {
        e.printStackTrace()
        Log.d("NFJF", " error is ${e.message}")

        null
    }
}
