package dx.queen.moviedbapplication.recyclerfView

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import dx.queen.moviedbproject.R
import dx.queen.moviedbproject.models.real_models.Movie
import kotlinx.android.synthetic.main.movie_view.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private var movieList = mutableListOf<Movie>()
    var clickOnItem = MutableLiveData<Movie>()

    fun setList(list: List<Movie>) {
        movieList = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_view, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movieList.size


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            clickOnItem.value = movieList[position]
        }

        holder.bind(movieList[position])
    }

}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Movie) {

        GlobalScope.launch {
            getBitmapFromURL(getMoviePosterUrl(movie.poster))?.let {
                withContext(Dispatchers.Main) {
                    itemView.iv_poster.setImageBitmap(it)

                }
            }
        }

        itemView.tv_name.text = movie.title
        itemView.tv_description.text = movie.overview

    }

    private fun getMoviePosterUrl(moviePoster: String): String {
        return if (moviePoster.startsWith("http")) {
            moviePoster
        } else {
            "http://image.tmdb.org/t/p/w300$moviePoster"
        }
    }


    private fun getBitmapFromURL(src: String?): Bitmap? {
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

}