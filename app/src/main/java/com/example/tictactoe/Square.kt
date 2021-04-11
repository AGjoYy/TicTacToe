package com.example.tictactoe

import android.R
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.util.AttributeSet
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView


@ExperimentalStdlibApi
@RequiresApi(Build.VERSION_CODES.N)
class Square(context: Context, attr: AttributeSet?) : AppCompatImageView(context, attr) {

    init {
        if(GameLogic.grid.size < 9)
            GameLogic.grid.add(this)

        this.setOnClickListener{
            GameLogic.MakeMove(this)
        }
    }
}