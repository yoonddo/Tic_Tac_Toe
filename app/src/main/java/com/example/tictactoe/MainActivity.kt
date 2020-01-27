package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var p1score = 0
    private var p2score = 0

    var turn = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnClick(view: View) {
        val bntSelected = view as Button
        var cellID = 0
        when(bntSelected.id) {
            R.id.button_00 -> cellID = 0
            R.id.button_01 -> cellID = 1
            R.id.button_02 -> cellID = 2
            R.id.button_10 -> cellID = 3
            R.id.button_11 -> cellID = 4
            R.id.button_12 -> cellID = 5
            R.id.button_20 -> cellID = 6
            R.id.button_21 -> cellID = 7
            R.id.button_22 -> cellID = 8
        }

        playGame(cellID, bntSelected)
    }

    fun resetBoard() {
        turn = 0
        player1.clear()
        player2.clear()

        button_00.isEnabled = true
        button_01.isEnabled = true
        button_02.isEnabled = true
        button_10.isEnabled = true
        button_11.isEnabled = true
        button_12.isEnabled = true
        button_20.isEnabled = true
        button_21.isEnabled = true
        button_22.isEnabled = true

        button_00.text = ""
        button_01.text = ""
        button_02.text = ""
        button_10.text = ""
        button_11.text = ""
        button_12.text = ""
        button_20.text = ""
        button_21.text = ""
        button_22.text = ""

    }
    fun resetClick(view: View) {
        p1score = 0
        p2score = 0
        text_view_p1.text = "Player 1: " + p1score
        text_view_p2.text = "Player 2: " + p2score

        resetBoard()
    }

    fun stopgame() {
        button_00.isEnabled = false
        button_01.isEnabled = false
        button_02.isEnabled = false
        button_10.isEnabled = false
        button_11.isEnabled = false
        button_12.isEnabled = false
        button_20.isEnabled = false
        button_21.isEnabled = false
        button_22.isEnabled = false
    }

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    var activePlayer = 1

    private fun playGame(cellID: Int, bntSelected: Button) {

        if (activePlayer == 1) {
            bntSelected.text = "X"
            player1.add(cellID)
            activePlayer = 2
        }
        else {
            bntSelected.text = "O"
            player2.add(cellID)
            activePlayer = 1
        }

        turn++
        bntSelected.isEnabled = false

        checkWinner()
    }

    private fun checkWinner() {
        var winner = -1

        // row 0
        if(player1.contains(0) && player1.contains(1) && player1.contains(2)) {
            winner = 1
        }
        if(player2.contains(0) && player2.contains(1) && player2.contains(2)) {
            winner = 2
        }
        // row 1
        if(player1.contains(3) && player1.contains(4) && player1.contains(5)) {
            winner = 1
        }
        if(player2.contains(3) && player2.contains(4) && player2.contains(5)) {
            winner = 2
        }
        // row 2
        if(player1.contains(6) && player1.contains(7) && player1.contains(8)) {
            winner = 1
        }
        if(player2.contains(6) && player2.contains(7) && player2.contains(8)) {
            winner = 2
        }

        // col 0
        if(player1.contains(0) && player1.contains(3) && player1.contains(6)) {
            winner = 1
        }
        if(player2.contains(0) && player2.contains(3) && player2.contains(6)) {
            winner = 2
        }
        // col 1
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if(player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }
        // col 2
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }

        // diag 0
        if(player1.contains(0) && player1.contains(4) && player1.contains(8)) {
            winner = 1
        }
        if(player2.contains(0) && player2.contains(4) && player2.contains(8)) {
            winner = 2
        }
        // diag 1
        if(player1.contains(2) && player1.contains(4) && player1.contains(6)) {
            winner = 1
        }
        if(player2.contains(2) && player2.contains(4) && player2.contains(6)) {
            winner = 2
        }


        when(winner) {
            1 -> {
                ++p1score
                text_view_p1.text = "Player 1: " + p1score

                val toast = Toast.makeText(this, "Player 1 won the GAME!", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()

                stopgame()
                resetBoard()
            }
            2 -> {
                ++p2score
                text_view_p2.text = "Player 2: " + p2score
                val toast = Toast.makeText(this, "Player 2 won the GAME!", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()

                stopgame()
                resetBoard()
            }
            else -> {
                if(turn == 9) {
                    val toast = Toast.makeText(this, "DRAW!", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                    resetBoard()
                }
            }
        }
    }
}
