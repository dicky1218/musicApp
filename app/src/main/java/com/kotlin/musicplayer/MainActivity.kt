package com.kotlin.musicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jean.jcplayer.model.JcAudio
import com.example.jean.jcplayer.view.JcPlayerView

class MainActivity : AppCompatActivity() {

    private lateinit var player : JcPlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player = findViewById(R.id.jcplayer)

        val jcAudios: ArrayList<JcAudio> = ArrayList()
        jcAudios.add(JcAudio.createFromURL("url audio", "http://xxx/audio.mp3"))

        player.initPlaylist(jcAudios, null)
    }


}