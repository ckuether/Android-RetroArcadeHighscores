package com.example.retroarcadehighscores

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.retroarcadehighscores.databinding.DialogHighscoreInputBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.Comparator

class HighscoreInputDialog(val game: Game, val highscorePos: Int = -1): DialogFragment(R.layout.dialog_highscore_input) {

    lateinit var binding: DialogHighscoreInputBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogHighscoreInputBinding.inflate(LayoutInflater.from(context))

        binding.gameName.text = game.name

        if(highscorePos != -1) {
            val highscore = game.highscores[highscorePos]
            binding.scoreInput.setText(highscore.score.toString())
            binding.initialsInput.setText(highscore.initials)
        }

        binding.cancelBtn.setOnClickListener {
            dismiss()
        }
        binding.updateBtn.setOnClickListener {
            val score = binding.scoreInput.text.toString().toInt()
            val initials = binding.initialsInput.text.toString()
            val highscore = Highscore(score, initials)

            val highscores = game.highscores
            if(highscorePos == -1) {
                highscores.add(highscore)
            }else{
                highscores[highscorePos] = highscore
            }

            Collections.sort(highscores, SortByScore())
            while(highscores.size > 10){
                highscores.removeAt(highscores.size-1)
            }
            game.highscores = highscores
            val db = FirebaseFirestore.getInstance()
            db.document("games/${game.id}").set(game)
            (activity as MainActivity).updateHighscores()
            dismiss()
        }

        val builder = AlertDialog.Builder(activity)
        builder.setView(binding.root)

        return builder.create()
    }
}

class SortByScore: Comparator<Highscore>{
    override fun compare(p0: Highscore, p1: Highscore): Int {
        return p1.score.compareTo(p0.score)
    }
}