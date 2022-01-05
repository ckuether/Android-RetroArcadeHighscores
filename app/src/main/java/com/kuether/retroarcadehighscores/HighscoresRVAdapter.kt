package com.kuether.retroarcadehighscores

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kuether.retroarcadehighscores.databinding.ViewHolderHighscoresBinding

class HighscoresRVAdapter(val mContext: Context, var game: Game?): RecyclerView.Adapter<HighscoresViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighscoresViewHolder {
        val binding = ViewHolderHighscoresBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return HighscoresViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HighscoresViewHolder, position: Int) {
        val highscore = game!!.highscores[position]

        holder.b.position.text = (position+1).toString()
        holder.b.score.text = highscore.score.toString()
        holder.b.initials.text = highscore.initials

        holder.b.container.setOnClickListener {
            val dialogFrag = HighscoreInputDialog(game!!, position)
            dialogFrag.show((mContext as MainActivity).supportFragmentManager, "highscores_input")
        }
    }

    override fun getItemCount(): Int {
        return game?.highscores?.size ?: 0
    }

    fun updateGame(game: Game){
        this.game = game
        notifyDataSetChanged()
    }
}

class HighscoresViewHolder(val b: ViewHolderHighscoresBinding): RecyclerView.ViewHolder(b.root)