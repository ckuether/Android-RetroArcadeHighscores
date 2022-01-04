package com.example.retroarcadehighscores

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retroarcadehighscores.databinding.ViewHolderHighscoresBinding

class HighscoresRVAdapter(val mContext: Context, val game: Game): RecyclerView.Adapter<HighscoresViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighscoresViewHolder {
        val binding = ViewHolderHighscoresBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return HighscoresViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HighscoresViewHolder, position: Int) {
        holder.b.position.text = (position+1).toString()
    }

    override fun getItemCount(): Int {
        return game.highscores.size
    }
}

class HighscoresViewHolder(val b: ViewHolderHighscoresBinding): RecyclerView.ViewHolder(b.root)