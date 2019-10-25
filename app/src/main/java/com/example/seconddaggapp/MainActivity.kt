package com.example.seconddaggapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.seconddaggapp.di.component.DaggerMainPresenterComponent
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener, Contract.ViewContract {

    @Inject
    lateinit var mainPresenter: MainApplicationPresenter

    override fun displayName(name: String) {
        Toast.makeText(this, "Main Activity ${name}", Toast.LENGTH_SHORT).show()
    }

    override fun onPageScrollStateChanged(state: Int) {
//        Do nothing
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        my_navigation_view.menu.getItem(position).isChecked = true
    }

    override fun onPageSelected(position: Int) {
//
    }

    private lateinit var pagerAdapter: FragmentPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as MainApplication).getDaggerPresenterComponent().inject(this)

        mainPresenter.setCurrentView(this)
        mainPresenter.getName()

        pagerAdapter = MyPagerAdapter(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        my_main_viewpager.adapter = pagerAdapter
        my_main_viewpager.addOnPageChangeListener(this)

        my_navigation_view.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.music_item -> my_main_viewpager.currentItem = 0
                R.id.album_item -> my_main_viewpager.currentItem = 1
                else -> my_main_viewpager.currentItem = 2
            }
            true
        }
    }
}

class MyPagerAdapter(fragmentManager: FragmentManager, behavior: Int) : FragmentPagerAdapter(fragmentManager, behavior) {
    override fun getItem(position: Int): Fragment {
        return MainFragment.getFragment(position)
    }

    override fun getCount(): Int {
        return MainFragment.fragmentCount
    }

}

class MainFragment : Fragment(), Contract.ViewContract {
    override fun displayName(name: String) {
        Log.d("TAG_X", "From Fragment: $name")
    }

    companion object {
        const val fragmentCount = 3
        const val FRAGMENT_KEY = "FRAGMENT_ID"

        fun getFragment(fragID: Int): Fragment {
            val fragment = MainFragment()
            val bundle = Bundle()
            bundle.putInt(FRAGMENT_KEY, fragID)
            fragment.arguments = bundle
            return fragment
        }
    }

    @Inject
    lateinit var mainPresenter: MainApplicationPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        lateinit var view: View
        arguments?.getInt(FRAGMENT_KEY)?.let { fragmentId ->
            view = when (fragmentId) {
                0 -> inflater.inflate(R.layout.fragment_one_layout, container, false)
                1 -> inflater.inflate(R.layout.fragment_two_layout, container, false)
                else -> inflater.inflate(R.layout.fragment_three_layout, container, false)
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.applicationContext as MainApplication).getDaggerPresenterComponent().inject(this)
        
        mainPresenter.setCurrentView(this)
        mainPresenter.getName()

    }
}