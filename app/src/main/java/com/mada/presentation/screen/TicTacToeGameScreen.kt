package com.mada.presentation.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mada.presentation.viewmodel.TicTacToeViewModel

@Composable
fun TicTacToeGameScreen(viewModel: TicTacToeViewModel) {
    val gameState = viewModel.gameState.collectAsState().value


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Tic Tac Toe",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // شبكة اللعبة 3x3
        Column(
            modifier = Modifier
                .border(2.dp, MaterialTheme.colorScheme.onSurface)
        ) {
            gameState.board.forEachIndexed { rowIndex, row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    row.forEachIndexed { colIndex, cell ->
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .border(1.dp, MaterialTheme.colorScheme.onSurface)
                                .clickable { viewModel.onCellClick(rowIndex, colIndex) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = cell?.toString() ?: "",
                                style = MaterialTheme.typography.headlineLarge
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.resetGame() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Reset Game")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = gameState.winner ?: "Current Player: ${gameState.currentPlayer.toString()}",
            style = MaterialTheme.typography.bodyLarge
        )

    }
}
