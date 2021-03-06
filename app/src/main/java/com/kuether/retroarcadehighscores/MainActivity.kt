package com.kuether.retroarcadehighscores

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.kuether.retroarcadehighscores.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var gamesFrag = GamesFragment.newInstance()

    var isTablet = false

    var selectedGame: Game? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isTablet = resources.getBoolean(R.bool.isTablet)

        binding.toolbar.toolbar.title = "Retro Arcade Scoreboard"
        binding.toolbar.toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back)
        binding.toolbar.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        if(isTablet){
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            attachHighscoresFrag()
        }

        attachGamesFrag()
    }

    fun updateHighscores(){
        val highscoresFrag = if(isTablet) {
            supportFragmentManager.findFragmentById(binding.highscoresCont!!.id) as HighscoresFragment
        }else{
            supportFragmentManager.findFragmentById(binding.container!!.id) as HighscoresFragment
        }
        highscoresFrag.updateHighscoresAdapter()
    }

    fun updateSelectedGame(game: Game){
        selectedGame = game
        if(isTablet) {
            val highscoresFrag = supportFragmentManager.findFragmentById(binding.highscoresCont!!.id) as HighscoresFragment
            highscoresFrag.updateGame(game)
        }else{
            attachHighscoresFrag()
        }
    }

    private fun attachGamesFrag(){
        val ft = supportFragmentManager.beginTransaction()
        if(isTablet){
            ft.replace(binding.gamesCont!!.id, gamesFrag).commit()
        }else{
            ft.replace(binding.container!!.id, gamesFrag).commit()
        }
    }

    private fun attachHighscoresFrag(){
        val ft = supportFragmentManager.beginTransaction()
        val highscoresFrag = HighscoresFragment.newInstance(selectedGame)
        if(isTablet){
            ft.replace(binding.highscoresCont!!.id, highscoresFrag).commit()
        }else{
            ft.replace(binding.container!!.id, highscoresFrag).commit()
        }
    }

    override fun onBackPressed() {
        if(!isTablet && supportFragmentManager.findFragmentById(binding.container!!.id) is HighscoresFragment){
            attachGamesFrag()
            return
        }
        super.onBackPressed()
    }
}