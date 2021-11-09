package dev.bahodir.youtubeapiapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.bahodir.youtubeapiapp.fragment.main.*
import dev.bahodir.youtubeapiapp.model.Item

class MainVPAdapter(fragmentActivity: FragmentActivity, private var list: List<Item>): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return FakeFragment.newInstance(list[position].id.videoId)
    }
}