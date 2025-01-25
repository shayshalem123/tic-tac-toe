package com.example.tic_tac_toe

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.tic_tac_toe.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    private var board = mutableListOf<Button>()

    companion object {
        const val X = "X"
        const val O = "O"
    }

    private var currentTurn = X;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)

        initBoard()
        resetGame()
    }

    private fun initBoard() {
        board.add(dataBinding.square11)
        board.add(dataBinding.square12)
        board.add(dataBinding.square13)
        board.add(dataBinding.square21)
        board.add(dataBinding.square22)
        board.add(dataBinding.square23)
        board.add(dataBinding.square31)
        board.add(dataBinding.square32)
        board.add(dataBinding.square33)

        updateTitle()
    }

    fun resetGame() {
        currentTurn = X

        for(button in board)
        {
            button.text = ""
        }
    }
    fun handleSquareClicked(view: View) {
        if (view !is Button) return

        updateBoard(view)

        if (hasPlayerWon(currentTurn)) return showResult()

        if (hasGameFinished()) return showResult(true)

        currentTurn = if (currentTurn == X) O else X;

        updateTitle()
    }

    private fun updateBoard(clickedButton: Button) {
        if (clickedButton.text != "") return

        clickedButton.text = currentTurn;
    }

    private fun hasGameFinished(): Boolean
    {
        for(button in board)
        {
            if(button.text == "")
                return false
        }

        return true
    }

    @SuppressLint("SetTextI18n")
    private fun updateTitle() {
        dataBinding.currentTurn.text = currentTurn;
    }

    private fun hasPlayerWon(player: String): Boolean {
        if (isBtnTextEquals(dataBinding.square11, player) &&
            isBtnTextEquals(dataBinding.square12, player) &&
            isBtnTextEquals(dataBinding.square13, player)
        )
            return true

        if (isBtnTextEquals(dataBinding.square21, player) &&
            isBtnTextEquals(dataBinding.square22, player) &&
            isBtnTextEquals(dataBinding.square23, player)
        )
            return true

        if (isBtnTextEquals(dataBinding.square31, player) &&
            isBtnTextEquals(dataBinding.square32, player) &&
            isBtnTextEquals(dataBinding.square33, player)
        )
            return true

        if (isBtnTextEquals(dataBinding.square13, player) &&
            isBtnTextEquals(dataBinding.square23, player) &&
            isBtnTextEquals(dataBinding.square33, player)
        )
            return true

        if (isBtnTextEquals(dataBinding.square11, player) &&
            isBtnTextEquals(dataBinding.square21, player) &&
            isBtnTextEquals(dataBinding.square31, player)
        )
            return true

        if (isBtnTextEquals(dataBinding.square12, player) &&
            isBtnTextEquals(dataBinding.square22, player) &&
            isBtnTextEquals(dataBinding.square32, player)
        )
            return true

        if (isBtnTextEquals(dataBinding.square11, player) &&
            isBtnTextEquals(dataBinding.square22, player) &&
            isBtnTextEquals(dataBinding.square33, player)
        )
            return true

        if (isBtnTextEquals(dataBinding.square13, player) &&
            isBtnTextEquals(dataBinding.square22, player) &&
            isBtnTextEquals(dataBinding.square31, player)
        )
            return true

        return false
    }

    private fun isBtnTextEquals(button: Button, symbol: String): Boolean = button.text == symbol

    private fun showResult(hasDrawn: Boolean = false)
    {
        val message = if(hasDrawn) "Draw" else "$currentTurn won"

        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Play again")
            { _,_ ->
                resetGame()
            }
            .setCancelable(false)
            .show()
    }
}