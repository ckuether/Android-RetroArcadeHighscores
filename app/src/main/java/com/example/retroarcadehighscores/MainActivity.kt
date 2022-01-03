package com.example.retroarcadehighscores

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.retroarcadehighscores.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.toolbar.title = "Retro Arcade Scoreboard"
        binding.toolbar.toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back)
        binding.toolbar.toolbar.setNavigationOnClickListener {
            super.onBackPressed()
        }

        if(resources.getBoolean(R.bool.isTablet)){
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        val db = FirebaseFirestore.getInstance()
        db.collection("games").orderBy("id").get().addOnSuccessListener {
            val games = arrayListOf<Game>()
            for(doc in it.documents){
                val game = doc.toObject(Game::class.java)!!
                games.add(game)
            }
            val test = games
            val test2 = "TEST"
        }
    }
}