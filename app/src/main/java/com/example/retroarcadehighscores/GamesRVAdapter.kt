package com.example.retroarcadehighscores

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retroarcadehighscores.databinding.ViewHolderGameBinding

class GamesRVAdapter(val mContext: Context, val games: ArrayList<Game>): RecyclerView.Adapter<GameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ViewHolderGameBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]

        holder.b.name.text = game.name
    }

    override fun getItemCount(): Int {
        return games.size
    }
}

class GameViewHolder(val b: ViewHolderGameBinding): RecyclerView.ViewHolder(b.root)