package com.example.tictactoe

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatTextView

object GameLogic : Application() {

    var context : Context? = null
    @ExperimentalStdlibApi
    var grid = mutableListOf<Square>()
    var isSingleGame = false
    private var isFirstPlayer = true

    @ExperimentalStdlibApi
    @RequiresApi(Build.VERSION_CODES.N)
    fun MakeMove(square: Square){
        if(square.drawable == null) {
            FillSquare(square)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @ExperimentalStdlibApi
    private fun FillSquare(square: Square){
        if(isSingleGame)
            FillSquareForSingleGame(square)
        else
            FillSquareForMultiplayerGame(square)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @ExperimentalStdlibApi
    private fun FillSquareForSingleGame(square: Square){
        if (isFirstPlayer) {
            square.setImageResource(R.drawable.x)
            square.tag = "x"
        } else {
            square.setImageResource(R.drawable.o)
            square.tag = "o"
        }

        isFirstPlayer = !isFirstPlayer
        CheckWinner()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @ExperimentalStdlibApi
    private fun FillSquareForMultiplayerGame(square: Square){
        square.setImageResource(R.drawable.x)
        square.tag = "x"
        isFirstPlayer = false

        if(CheckWinner())
            return

        val randomSquare = grid.filter { s -> s.drawable == null }.randomOrNull()
        randomSquare?.setImageResource(R.drawable.o)
        randomSquare?.tag = "o"

        if(randomSquare != null)
            isFirstPlayer = true

        CheckWinner()
    }

    @ExperimentalStdlibApi
    @RequiresApi(Build.VERSION_CODES.N)
    private fun CheckWinner() : Boolean{
        var isWinner: Boolean
        var horizontal = 0
        var vertical = 0

        isWinner = (grid[4].drawable != null) && // check if center not null
                   ((grid[0].tag == grid[4].tag && grid[0].tag == grid[8].tag) || // check first diagonal
                   (grid[2].tag == grid[4].tag && grid[2].tag == grid[6].tag))  // check second diagonal

        while(vertical < 3 && !isWinner) {

            if ((grid[vertical].drawable != null || grid[horizontal].drawable != null) &&
                       ((grid[horizontal].tag == grid[horizontal + 1].tag && grid[horizontal].tag == grid[horizontal + 2].tag) || // check horizontal lines
                       (grid[vertical].tag == grid[vertical + 3].tag && grid[vertical].tag == grid[vertical + 6].tag))) // check vertical lines
            {
                isWinner = true
                break
            }
            horizontal += 3
            vertical += 1
        }

        val allSquaresAreFilled = grid.all { s -> s.drawable != null }

        if(isWinner || (!isWinner && allSquaresAreFilled)) {
            var congratulationText = ""
            if (isWinner) {
                congratulationText = if (!isFirstPlayer) "First player has won" else "Second player has won"
            } else if (!isWinner && allSquaresAreFilled) {
                congratulationText = "It's draw"
            }
            OpenWinnerPopUpWindow(congratulationText)
            ResetGame()
        }
        return isWinner
    }

    private fun OpenWinnerPopUpWindow(congratulationText: String){
        val dialogBuilder = AlertDialog.Builder(context)
        val winningPopUpWindow = LayoutInflater.from(context).inflate(R.layout.winner_pop_up, null)
        winningPopUpWindow.findViewById<AppCompatTextView>(R.id.popup_window_text)?.text = congratulationText
        dialogBuilder.setView(winningPopUpWindow)
        dialogBuilder.setCancelable(false)
        val dialog = dialogBuilder.create()
        dialog.show()
    }

    @ExperimentalStdlibApi
    @RequiresApi(Build.VERSION_CODES.N)
    private fun ResetGame(){
        isFirstPlayer = true
        grid.clear()
    }
}