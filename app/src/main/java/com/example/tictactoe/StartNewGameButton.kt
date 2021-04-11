package com.example.tictactoe

import android.R
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton

class StartNewGameButton(context: Context, attr: AttributeSet?) : AppCompatButton(context, attr) {

    init {
        this.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(context, com.example.tictactoe.R.anim.button_anim)
            it.startAnimation(animation)

            val intent = Intent(context, MainActivity::class.java)
            val options = ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out)
            context.startActivity(intent, options.toBundle())
        }
    }
}