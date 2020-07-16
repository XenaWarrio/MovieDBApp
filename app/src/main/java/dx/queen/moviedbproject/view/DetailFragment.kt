package dx.queen.moviedbproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dx.queen.moviedbproject.R
import dx.queen.moviedbproject.convertImage.getBitmapFromURL
import dx.queen.moviedbproject.convertImage.getMoviePosterUrl
import dx.queen.moviedbproject.models.real_models.MovieDetail
import dx.queen.moviedbproject.viewModel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailFragment : Fragment() {

    private var genreString = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        val movieId = requireArguments().getInt("movie_id")
        viewModel.getMovieDetail(movieId)

        val movieObserver = Observer<MovieDetail> {
            with(it) {
                GlobalScope.launch {
                    backdrop_path?.let {
                        getBitmapFromURL(getMoviePosterUrl(backdrop_path))?.let {
                            withContext(Dispatchers.Main) {
                                imageView.setImageBitmap(it)

                            }
                        }
                    }
                }

                genres.forEach {
                    makeGenresString(it.name)
                }
                tv_genres.text = genreString
                tv_name.text = title
                tv_cast.text = "$budget$"
                tv_release_date.text = release_date
                overview?.let {
                    tv_description.text = it
                }
            }
        }

        viewModel.movieLiveData.observe(viewLifecycleOwner, movieObserver)
    }

    private fun makeGenresString(name: String) {
        genreString += " $name"
    }
}