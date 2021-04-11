package com.example.tictactoe

import android.R
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.AppCompatButton


class MultiPlayerGameButton(context: Context, attrs: AttributeSet?) :
    AppCompatButton(context, attrs) {

    init {
        this.setOnClickListener{
            val animation = AnimationUtils.loadAnimation(context, com.example.tictactoe.R.anim.button_anim)
            it.startAnimation(animation)

            GameLogic.isSingleGame = false
            val intent = Intent(context, GameActivity::class.java)

            val options = ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out)
            context.startActivity(intent, options.toBundle())
        }
    }
}