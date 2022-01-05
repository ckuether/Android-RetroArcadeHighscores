package com.kuether.retroarcadehighscores

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kuether.retroarcadehighscores.databinding.FragmentGamesBinding
import com.google.firebase.firestore.FirebaseFirestore

class GamesFragment: Fragment(R.layout.fragment_games) {

    lateinit var binding: FragmentGamesBinding
    var gamesAdapter: GamesRVAdapter? = null
    lateinit var mContext: Context
    var games = arrayListOf<Game>()

    companion object{
        fun newInstance(): GamesFragment{
            val frag = GamesFragment()
            return frag
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGamesBinding.bind(view)
        mContext = requireContext()
    }

    override fun onResume() {
        super.onResume()
        if(gamesAdapter == null) {
            val db = FirebaseFirestore.getInstance()
            db.collection("games").orderBy("id").get().addOnSuccessListener {
                games = arrayListOf()
                for (doc in it.documents) {
                    val game = doc.toObject(Game::class.java)!!
                    games.add(game)
                }
                gamesAdapter = GamesRVAdapter(mContext, games)
                updateViews()
            }
        }else{
            updateViews()
        }
    }

    fun updateViews(){
        binding.gamesRv.layoutManager = LinearLayoutManager(context)
        binding.gamesRv.adapter = gamesAdapter
        if(context != null && (context as MainActivity).isTablet) {
            (context as MainActivity).updateSelectedGame(games[0])
        }
    }
}