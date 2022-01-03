package com.example.retroarcadehighscores

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.retroarcadehighscores.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var gamesFrag = GamesFragment.newInstance()

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

        attachGamesFrag()
    }

    private fun attachGamesFrag(){
        val ft = supportFragmentManager.beginTransaction()
        if(resources.getBoolean(R.bool.isTablet)){
            ft.replace(binding.gamesCont!!.id, gamesFrag).commit()
        }else{
            ft.replace(binding.container!!.id, gamesFrag).commit()
        }
    }
}