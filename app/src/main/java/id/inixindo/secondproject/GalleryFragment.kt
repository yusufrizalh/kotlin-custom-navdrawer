package id.inixindo.secondproject

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_gallery.view.*

class GalleryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, root: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_gallery, root, false)

        view.tab_layout.setSelectedTabIndicatorColor(Color.WHITE)
        view.tab_layout.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.purple_700
            )
        )
        view.tab_layout.tabTextColors =
            ContextCompat.getColorStateList(requireContext(), android.R.color.white)

        val numberOfTabs = 3

        view.tab_layout.tabMode = TabLayout.MODE_FIXED

        view.tab_layout.isInlineLabel = true

        val adapter =
            TabsPagerAdapter(requireActivity().supportFragmentManager, lifecycle, numberOfTabs)
        view.tabs_viewpager.adapter = adapter

        view.tabs_viewpager.isUserInputEnabled = true

        TabLayoutMediator(view.tab_layout, view.tabs_viewpager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Vacation"
                    tab.setIcon(R.drawable.icon_gallery)
                }
                1 -> {
                    tab.text = "Workshop"
                    tab.setIcon(R.drawable.icon_gallery)
                }
                2 -> {
                    tab.text = "Activity"
                    tab.setIcon(R.drawable.icon_gallery)
                }
                else -> HomeFragment()
            }

            tab.icon?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                Color.WHITE, BlendModeCompat.SRC_ATOP
            )
        }.attach()

        return view
    }
}