package com.example.tictactoe

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class SingleGameButton(context: Context, attrs: AttributeSet?) :
    AppCompatButton(context, attrs) {

    init {
        this.setOnClickListener{
            GameLogic.isSingleGame = true

            val intent = Intent(context, GameActivity::class.java)
            context.startActivity(intent)
        }
    }
}