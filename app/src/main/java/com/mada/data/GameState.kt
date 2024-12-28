package com.mada.data

data class GameState(
    val board: List<List<Char?>> = List(3) { List(3) { null } },
    val currentPlayer: Char = 'X',
    val winner: String? = null,
    val winnerLine: List<Pair<Int, Int>> = emptyList()
)
