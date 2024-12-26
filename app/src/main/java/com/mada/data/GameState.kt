package com.mada.data

data class GameState(
    val board: List<List<Char?>>,
    val currentPlayer: Char,
    val winner: String? = null
)
