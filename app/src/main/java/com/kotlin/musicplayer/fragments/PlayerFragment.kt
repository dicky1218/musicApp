package com.kotlin.musicplayer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jean.jcplayer.model.JcAudio
import com.example.jean.jcplayer.view.JcPlayerView
import com.kotlin.musicplayer.R
import com.kotlin.musicplayer.databinding.FragmentPlayerBinding

class PlayerFragment : Fragment() {

    private lateinit var player : JcPlayerView  // define player use Jcplayer
    private lateinit var binding: FragmentPlayerBinding // binding fragment XML

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayerBinding.inflate(layoutInflater) // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get firebase storage urls
        var url1 = "https://firebasestorage.googleapis.com/v0/b/musicplayer-147fc.appspot.com/o/song1.mp3?alt=media&token=284f6e17-e9da-4069-9cf5-df98b05bd338"
        var url2 = "https://firebasestorage.googleapis.com/v0/b/musicplayer-147fc.appspot.com/o/song2.mp3?alt=media&token=00635209-5930-438d-bd7b-d89954b097c6"

        // call Jcplayer from XML file
        var player = binding.jcplayer

        // create arraylist store url to play song
        val jcAudios: ArrayList<JcAudio> = ArrayList()
        jcAudios.add(JcAudio.createFromURL("Memory of the wind", url1))
        jcAudios.add(JcAudio.createFromURL("Flower Road", url2))

        // init playlist
        player.initPlaylist(jcAudios, null)

        // build notification function in the phone
        player.createNotification()
    }
}