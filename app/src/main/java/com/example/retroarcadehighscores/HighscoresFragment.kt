package com.example.retroarcadehighscores

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retroarcadehighscores.databinding.FragmentHighscoresBinding

class HighscoresFragment(var selectedGame: Game? = null): Fragment(R.layout.fragment_highscores) {

    lateinit var binding: FragmentHighscoresBinding
    lateinit var highscoresAdapter: HighscoresRVAdapter

    companion object{
        fun newInstance(game: Game?): HighscoresFragment{
            val frag = HighscoresFragment(game)
            return frag
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHighscoresBinding.bind(view)

        binding.highscoresRv.layoutManager = LinearLayoutManager(context)
        highscoresAdapter = HighscoresRVAdapter(requireContext(), selectedGame)
        binding.highscoresRv.adapter = highscoresAdapter

        if(!resources.getBoolean(R.bool.isTablet)) {
            updateGame(selectedGame!!)
        }
    }

    fun updateHighscoresAdapter(){
        highscoresAdapter.notifyDataSetChanged()
    }

    fun updateGame(game: Game){
        selectedGame = game

        binding.gameName.text = selectedGame?.name
        highscoresAdapter.updateGame(game)

        binding.highscoreFab.setOnClickListener {
            val dialogFrag = HighscoreInputDialog(selectedGame!!)
            dialogFrag.show(requireActivity().supportFragmentManager, "highscores_input")
        }
    }
}