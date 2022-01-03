package com.example.retroarcadehighscores

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.retroarcadehighscores.databinding.FragmentGamesBinding

class GamesFragment: Fragment(R.layout.fragment_games) {

    lateinit var binding: FragmentGamesBinding

    companion object{
        fun newInstance(): GamesFragment{
            val frag = GamesFragment()
            return frag
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGamesBinding.bind(view)
    }
}