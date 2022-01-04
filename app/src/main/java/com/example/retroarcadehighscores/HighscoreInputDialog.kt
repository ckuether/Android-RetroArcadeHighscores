package com.example.retroarcadehighscores

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.retroarcadehighscores.databinding.DialogHighscoreInputBinding

class HighscoreInputDialog: DialogFragment(R.layout.dialog_highscore_input) {

    lateinit var binding: DialogHighscoreInputBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogHighscoreInputBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(activity)
        builder.setView(binding.root)

        return builder.create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cancelBtn.setOnClickListener {
            dismiss()
        }
        binding.updateBtn.setOnClickListener {
            Toast.makeText(context, "TEST", Toast.LENGTH_SHORT).show()
        }
    }
}