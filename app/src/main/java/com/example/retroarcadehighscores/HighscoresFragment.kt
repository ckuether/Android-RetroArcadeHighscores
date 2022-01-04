package com.example.retroarcadehighscores

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.retroarcadehighscores.databinding.FragmentHighscoresBinding

class HighscoresFragment(var selectedGame: Game? = null): Fragment(R.layout.fragment_highscores) {

    lateinit var binding: FragmentHighscoresBinding

    companion object{
        fun newInstance(): HighscoresFragment{
            val frag = HighscoresFragment()
            return frag
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHighscoresBinding.bind(view)
    }

    fun updateGame(game: Game){
        selectedGame = game

        binding.gameName.text = selectedGame?.name
    }
}