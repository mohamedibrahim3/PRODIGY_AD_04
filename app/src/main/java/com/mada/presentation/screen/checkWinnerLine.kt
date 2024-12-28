package com.mada.presentation.screen

fun CheckWinnerLine(winnerLine: List<Pair<Int, Int>>, rowIndex: Int, colIndex: Int): Boolean {
    return winnerLine.contains(Pair(rowIndex, colIndex))
}