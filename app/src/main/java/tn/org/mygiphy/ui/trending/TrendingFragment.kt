package tn.org.mygiphy.ui.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tn.org.mygiphy.databinding.TrendingLayoutBinding


@AndroidEntryPoint
class TrendingFragment : Fragment() {

    private lateinit var binding: TrendingLayoutBinding
    private val viewModel by viewModels<TrendingViewModel>()
    private var searchJob: Job? = null
    private val trendingAdapter = TrendingAdapter()

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
            search(it.toString())
        }
        search("")
    }

    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            delay(300)
            viewModel.search(query).collectLatest {
                trendingAdapter.submitData(it)
                binding.recyclerView.isVisible = true
                binding.loader.isVisible = false
            }
        }
    }
}