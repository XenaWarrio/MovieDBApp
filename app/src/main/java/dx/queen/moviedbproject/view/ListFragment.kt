package dx.queen.moviedbproject.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import dx.queen.moviedbapplication.recyclerfView.MovieAdapter
import dx.queen.moviedbproject.R
import dx.queen.moviedbproject.databinding.FragmentListBinding
import dx.queen.moviedbproject.models.real_models.Movie
import dx.queen.moviedbproject.viewModel.ListViewModel

class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_list, container, false
        )
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ac = activity as MainActivity

        val viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.getMovieList()

        binding.pbLoading.visibility = View.VISIBLE

        val movieAdapter = MovieAdapter()
        with(binding.recycer) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = movieAdapter
            setHasFixedSize(true)
        }

        val clickOnMovieObserver = Observer<Movie> {
            ac.openDetails(it.id)
        }

        val movieListObserver = Observer<List<Movie>> {
            binding.pbLoading.visibility = View.GONE
            movieAdapter.setList(it)
        }

        val activeBackObserver = Observer<Boolean> {
            if (it) {
                binding.btBack.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_back_active)
                binding.btBack.isClickable = true
            } else {

                binding.btBack.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_back_noactive)
                binding.btBack.isClickable = false

                binding.etSearch.text.clear()
            }
        }

        movieAdapter.clickOnItem.observe(viewLifecycleOwner, clickOnMovieObserver)
        viewModel.movieListLiveData.observe(viewLifecycleOwner, movieListObserver)
        viewModel.backIsActive.observe(viewLifecycleOwner, activeBackObserver)

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().trim().isNotEmpty()) {
                    binding.btMakeSearch.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.ic_search_active)
                    binding.btMakeSearch.isClickable = true
                }
            }

        })
    }


}