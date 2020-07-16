package dx.queen.moviedbproject.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dx.queen.moviedbproject.models.real_models.MovieDetail
import dx.queen.moviedbproject.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val repository =
        Repository()

    val movieLiveData = MutableLiveData<MovieDetail>()

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val movie = repository.getMovieDetail(movieId)
            movieLiveData.postValue(movie)
        }
    }
}