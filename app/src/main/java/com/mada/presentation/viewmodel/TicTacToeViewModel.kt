package com.mada.presentation.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.mada.data.GameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TicTacToeViewModel(context: Context) : ViewModel() {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("TicTacToePrefs", Context.MODE_PRIVATE)

    private val _gameState = MutableStateFlow(GameState(board = List(3) { List(3) { null } }, currentPlayer = 'X'))
    val gameState: StateFlow<GameState> = _gameState

    init {
        loadGameState()
    }

    fun onCellClick(row: Int, col: Int) {
        val state = _gameState.value
        if (state.board[row][col] == null && state.currentPlayer != 'X' && state.currentPlayer != 'O') {
            val newBoard = state.board.toMutableList()
            newBoard[row] = newBoard[row].toMutableList().apply { this[col] = state.currentPlayer }
            val nextPlayer = if (state.currentPlayer == 'X') 'O' else 'X'
            _gameState.value = GameState(newBoard, nextPlayer)

            if (checkWin(newBoard)) {
                val winner = "${state.currentPlayer} Wins!"
                _gameState.value = _gameState.value.copy(currentPlayer = if (state.currentPlayer == 'X') 'O' else 'X', winner = winner)
                saveGameState()
            } else {
                saveGameState()
            }
        }
    }

    fun resetGame() {
        _gameState.value = GameState(board = List(3) { List(3) { null } }, currentPlayer = 'X')
        saveGameState()
    }

    private fun checkWin(board: List<List<Char?>>): Boolean {
        for (i in 0 until 3) {
            if (board[i][0] != null && board[i][0] == board[i][1] && board[i][1] == board[i][2]) return true
            if (board[0][i] != null && board[0][i] == board[1][i] && board[1][i] == board[2][i]) return true
        }
        if (board[0][0] != null && board[0][0] == board[1][1] && board[1][1] == board[2][2]) return true
        if (board[0][2] != null && board[0][2] == board[1][1] && board[1][1] == board[2][0]) return true
        return false
    }

    private fun saveGameState() {
        val board = _gameState.value.board.flatten().joinToString(",")
        val currentPlayer = _gameState.value.currentPlayer.toString()
        sharedPreferences.edit().apply {
            putString("board", board)
            putString("currentPlayer", currentPlayer)
            apply()
        }
    }

    private fun loadGameState() {
        val boardString = sharedPreferences.getString("board", "")
        val currentPlayerString = sharedPreferences.getString("currentPlayer", "X")

        if (!boardString.isNullOrEmpty()) {
            val board = boardString.split(",").chunked(3).map {
                it.map { char -> if (char == "null") null else char[0] }
            }
            _gameState.value = GameState(board = board, currentPlayer = currentPlayerString?.get(0) ?: 'X')
        }
    }
}