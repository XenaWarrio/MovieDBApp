package dx.queen.moviedbproject.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dx.queen.moviedbproject.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.navigate(R.id.listFragment)
    }

    fun openDetails(id: Int) {
        val bundle = bundleOf("movie_id" to id)
        navController.navigate(R.id.action_listFragment_to_detailFragment, bundle)
    }


}