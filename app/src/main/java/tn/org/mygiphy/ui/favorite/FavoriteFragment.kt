package tn.org.mygiphy.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import tn.org.mygiphy.databinding.FavoriteGifLayoutBinding

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FavoriteGifLayoutBinding
    private val viewModel: FavoriteViewModel by viewModels()
    private val favoriteAdapter = FavoriteAdapter {
        viewModel.delete(item = it)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavoriteGifLayoutBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = favoriteAdapter
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launchWhenResumed {
            viewModel.loadFavorite().collect {
                if(it.isNotEmpty()){
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.animationView.visibility = View.GONE
                    favoriteAdapter.submitList(it)
                }else{
                    binding.recyclerView.visibility = View.GONE
                    binding.animationView.visibility = View.VISIBLE
                }
            }
        }
    }

}