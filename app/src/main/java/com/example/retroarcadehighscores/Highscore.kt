package com.example.retroarcadehighscores

import com.google.firebase.Timestamp


class Highscore() {
    var score: Int = -1
    var initials: String = ""
    var timestamp: Timestamp? = null

    constructor(score: Int, initials: String): this(){
        this.score = score
        this.initials = initials
        timestamp = Timestamp.now()
    }
}