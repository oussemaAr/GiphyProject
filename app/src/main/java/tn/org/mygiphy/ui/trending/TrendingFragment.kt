package tn.org.mygiphy.ui.trending

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tn.org.mygiphy.databinding.TrendingLayoutBinding


@AndroidEntryPoint
class TrendingFragment : Fragment() {

    private var lastQuery = ""
    private lateinit var binding: TrendingLayoutBinding
    private val viewModel by viewModels<TrendingViewModel>()
    private var searchJob: Job? = null
    private val trendingAdapter = TrendingAdapter {
        lifecycleScope.launchWhenCreated {
            viewModel.favorites(item = it).collectLatest { id ->
                val message = if (id != -1L)
                    "Gif Added to favorites"
                else
                    "Gif removed from favorite"
                Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TrendingLayoutBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.adapter = trendingAdapter
        binding.query.doAfterTextChanged {
            lastQuery = it.toString()
            search()
        }
        trendingAdapter.addLoadStateListener {
            Log.e("TAG", "onViewCreated: $it")
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    binding.loader.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.animationView.visibility = View.GONE
                }
                LoadState.Loading -> {
                    binding.loader.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.animationView.visibility = View.GONE
                }
                is LoadState.Error -> {
                    binding.loader.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                    binding.animationView.visibility = View.VISIBLE
                    Snackbar.make(
                        binding.root,
                        "Internet Problem Please retry",
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction("Reload") {
                        search()
                    }.show()
                }
            }
        }
        search()
    }

    private fun search() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch(Dispatchers.Main) {
            delay(300)
            viewModel.search(lastQuery).collectLatest {
                trendingAdapter.submitData(it)
            }
        }
    }
}