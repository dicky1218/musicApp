package com.kotlin.musicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kotlin.musicplayer.fragments.MapFragment
import com.kotlin.musicplayer.fragments.PlayerFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // define fragment variables
    private val playerFragment = PlayerFragment()
    private val mapFragment = MapFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(playerFragment) // call replaceFragment, start on playerFragment

        // click nav icon to switch fragment
        bottom_nav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.music_dashboard -> replaceFragment(playerFragment)
                R.id.map_dashboard -> replaceFragment(mapFragment)
            }
            true
        }
    }

    // change fragment between all fragments
    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}