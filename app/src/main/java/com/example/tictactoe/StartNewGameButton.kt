package com.example.tictactoe

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton

class StartNewGameButton(context: Context, attr: AttributeSet?) : AppCompatButton(context, attr) {

    init {
        this.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}