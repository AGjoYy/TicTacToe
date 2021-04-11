package com.example.tictactoe

import android.content.Context
import android.os.Build
import android.util.AttributeSet
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