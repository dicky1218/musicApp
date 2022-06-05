package com.kotlin.musicplayer

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.jean.jcplayer.model.JcAudio
import com.example.jean.jcplayer.view.JcPlayerView
import com.google.firebase.firestore.FirebaseFirestore

data class Song (
    val songUrl: String = ""
)

class MainActivity : AppCompatActivity() {

    private lateinit var player : JcPlayerView  // define player use Jcplayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val db = FirebaseFirestore.getInstance()
//        val availableTimesRef = db.collection("song").document("songs")
//        availableTimesRef.get().addOnCompleteListener {
//            if (it.isSuccessful) {
//                val data = it.result.data
//                data?.let {
//                    for ((key, value) in data) {
//                        val v = value as Map<*, *>
//                        val songUrl = v["songUrl"]
//                        Log.d(TAG, "$key -> $songUrl")
//                    }
//                }
//            }
//        }

        // get firebase storage a url
        var url1 = "https://firebasestorage.googleapis.com/v0/b/musicplayer-147fc.appspot.com/o/song1.mp3?alt=media&token=284f6e17-e9da-4069-9cf5-df98b05bd338"
        var url2 = "https://firebasestorage.googleapis.com/v0/b/musicplayer-147fc.appspot.com/o/song2.mp3?alt=media&token=00635209-5930-438d-bd7b-d89954b097c6"
        player = findViewById(R.id.jcplayer) // find jcplayer xml id

        // create arraylist store url to play song
        val jcAudios: ArrayList<JcAudio> = ArrayList()
        jcAudios.add(JcAudio.createFromURL("Memory of the wind", url1))
        jcAudios.add(JcAudio.createFromURL("Flower Road", url2))

        player.initPlaylist(jcAudios, null)

        player.createNotification()
    }
}