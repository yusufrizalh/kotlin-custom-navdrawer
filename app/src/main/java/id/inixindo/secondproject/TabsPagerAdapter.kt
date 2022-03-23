package id.inixindo.secondproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabsPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle, private var numberOfTabs: Int) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int {
        return numberOfTabs
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                val bundle = Bundle()
                bundle.putString("fragmentName", "Vacation Gallery")
                val vacationFragment = VacationFragment()
                vacationFragment.arguments = bundle
                return vacationFragment
            }
            1 -> {
                val bundle = Bundle()
                bundle.putString("fragmentName", "Workshop Gallery")
                val workshopFragment = WorkshopFragment()
                workshopFragment.arguments = bundle
                return workshopFragment
            }
            2 -> {
                val bundle = Bundle()
                bundle.putString("fragmentName", "Activity Gallery")
                val activityFragment = ActivityFragment()
                activityFragment.arguments = bundle
                return activityFragment
            }
            else -> return HomeFragment()
        }
    }
}