package com.kotlin.musicplayer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kotlin.musicplayer.R
import com.kotlin.musicplayer.databinding.FragmentMapBinding

class MapFragment : Fragment() {

    private lateinit var mMap: GoogleMap    // define Google map var
    private lateinit var binding: FragmentMapBinding    // binding mapFragment XML

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(layoutInflater) // Inflate the layout for this fragment

        // use childFragmentManager to support Google map run in fragment
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment!!.getMapAsync { mMap ->
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

            mMap.clear() // clear old markers

            // define google map start point
            // Hong Kong as a start point
            val googlePlex = CameraPosition.builder()
                .target(LatLng(22.302711, 114.177216))
                .zoom(10f)
                .bearing(0f)
                .tilt(45f)
                .build()

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null)

            // add map marker
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(22.316092887435357, 114.17023914836717))
                        .title("Mini Shop")
            )

            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(22.382595719669844, 114.18804941173322))
                    .title("CD Warehouse, ShaTin")
            )

            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(22.311244449464542, 114.18807972728199))
                    .title("Music Store 1993")
            )
        }
        return binding.root
    }
}