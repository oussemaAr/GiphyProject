package tn.org.mygiphy.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import tn.org.mygiphy.ui.favorite.FavoriteFragment
import tn.org.mygiphy.ui.trending.TrendingFragment

class GiphyFragmentAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int) =
        if (position == 0) TrendingFragment() else FavoriteFragment()
}