package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var player1Turn = true
    private lateinit var gameList : List<TableCell>
    private var lockGame = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.text = "Player 1's Turn"
        gameList = listOf(TopLeft_TableCell, TopMiddle_TableCell, TopRight_TableCell,
            MiddleLeft_TableCell, MiddleMiddle_TableCell, MiddleRight_TableCell,
            BottomLeft_TableCell, BottomMiddle_TableCell, BottomRight_TableCell)

        setupClickListeners()

    }

    private fun setupClickListeners(){

        for(x in gameList){
            x.setOnClickListener {
                if(!lockGame) {
                    if (player1Turn) {
                        x.changeState(1)
                        player1Turn = false
                        textView.text = "Player 2's Turn"
                    } else {
                        x.changeState(2)
                        player1Turn = true
                        textView.text = "Player 1's Turn"
                    }
                    checkWinConditions()
                }
            }
        }

        resetButton.setOnClickListener{
            for(x in gameList){
                textView.text = "Player 1's Turn"
                x.changeState(0)
                lockGame = false
            }
        }
    }

    private fun checkWinConditions(){
        //Horizontal Check
        for(x in 0 until gameList.size -1 step 3){
             if(gameList[x].getState() == 1 && gameList[x+1].getState() == 1 && gameList[x+2].getState() == 1){
                 textView.text = "Player One Wins"
                 lockGame = true
             }
            else if (gameList[x].getState() == 2 && gameList[x+1].getState() == 2 && gameList[x+2].getState() == 2){
                 textView.text = "Player Two Wins"
                 lockGame = true
             }
        }

        //Vertical Check
        for(x in 0 until 3){
            if(gameList[x].getState() == 1 && gameList[x+3].getState() == 1 && gameList[x+6].getState() == 1){
                textView.text = "Player One Wins"
                lockGame = true
            }
            else if (gameList[x].getState() == 2 && gameList[x+3].getState() == 2 && gameList[x+6].getState() == 2){
                textView.text = "Player Two Wins"
                lockGame = true
            }
        }

        //Diagonal Check
        if(gameList[0].getState() == 1 && gameList[4].getState() == 1 && gameList[8].getState() == 1){
            textView.text = "Player One Wins"
            lockGame = true
        }
        else if (gameList[0].getState() == 2 && gameList[4].getState() == 2 && gameList[8].getState() == 2){
            textView.text = "Player Two Wins"
            lockGame = true
        }

        if(gameList[2].getState() == 1 && gameList[4].getState() == 1 && gameList[6].getState() == 1){
            textView.text = "Player One Wins"
            lockGame = true
        }
        else if (gameList[2].getState() == 2 && gameList[4].getState() == 2 && gameList[6].getState() == 2){
            textView.text = "Player Two Wins"
            lockGame = true
        }
    }



}
