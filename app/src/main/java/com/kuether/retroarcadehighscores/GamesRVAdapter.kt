package com.kuether.retroarcadehighscores

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kuether.retroarcadehighscores.databinding.ViewHolderGameBinding

class GamesRVAdapter(val mContext: Context, val games: ArrayList<Game>): RecyclerView.Adapter<GameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ViewHolderGameBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        var gameId = (game.id+1).toString()
        while(gameId.length < 3){
            gameId = "0$gameId"
        }

        holder.b.id.text = gameId
        holder.b.name.text = game.name
        holder.b.container.setOnClickListener {
            (mContext as MainActivity).updateSelectedGame(game)
        }
    }

    override fun getItemCount(): Int {
        return games.size
    }
}

class GameViewHolder(val b: ViewHolderGameBinding): RecyclerView.ViewHolder(b.root)