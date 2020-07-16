package dx.queen.moviedbproject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dx.queen.moviedbproject.models.real_models.Movie
import dx.queen.moviedbproject.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private val repository =
        Repository()


    private val movieListMutableLiveData = MutableLiveData<List<Movie>>()
    val movieListLiveData: LiveData<List<Movie>> = movieListMutableLiveData

    var editTextResult = MutableLiveData<String>()
    var backIsActive = MutableLiveData<Boolean>()


    fun getMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getResponse()

            movieListMutableLiveData.postValue(result.results)
            backIsActive.postValue(false)
        }
    }

    fun findMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            editTextResult.value?.let {
                val result = repository.findMovies(it)

                movieListMutableLiveData.postValue(result.results)
                backIsActive.postValue(true)
            }
        }
    }


}