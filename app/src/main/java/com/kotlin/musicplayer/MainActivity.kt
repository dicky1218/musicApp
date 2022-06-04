package com.kotlin.musicplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jean.jcplayer.model.JcAudio
import com.example.jean.jcplayer.view.JcPlayerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private lateinit var player : JcPlayerView  // define player use Jcplayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get firebase storage a url
        val url1 = "https://firebasestorage.googleapis.com/v0/b/musicplayer-147fc.appspot.com/o/Naul%20%20-%20Memory%20Of%20The%20Wind%20%5Bmp3clan.com%5D.mp3?alt=media&token=41a84036-d4e2-40b9-927a-930bb84e9309"
        val url2 = "https://firebasestorage.googleapis.com/v0/b/musicplayer-147fc.appspot.com/o/01.%20%EA%BD%83%EA%B8%B8%20(Prod.%20By%20%EC%A7%80%EC%BD%94(ZICO)).mp3?alt=media&token=51ed2724-cf90-40ba-928f-f00924527ce1"

        player = findViewById(R.id.jcplayer) // find jcplayer xml id

        // create arraylist store url to play song
        val jcAudios: ArrayList<JcAudio> = ArrayList()
        jcAudios.add(JcAudio.createFromURL("Memory of the wind", url1))
        jcAudios.add(JcAudio.createFromURL("Flower Road", url2))

        player.initPlaylist(jcAudios, null)

        player.createNotification()
    }
}