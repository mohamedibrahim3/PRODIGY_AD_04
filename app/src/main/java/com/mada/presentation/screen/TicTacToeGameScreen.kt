package com.mada.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mada.presentation.viewmodel.TicTacToeViewModel
import com.mada.tictactoe.ui.theme.backgroundColor
import com.mada.tictactoe.ui.theme.cellBackgroundColor
import com.mada.tictactoe.ui.theme.primaryColor
import com.mada.tictactoe.ui.theme.secondaryColor
import com.mada.tictactoe.ui.theme.textColor

@Composable
fun TicTacToeGameScreen(viewModel: TicTacToeViewModel) {
    val gameState = viewModel.gameState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Tic Tac Toe",
            style = MaterialTheme.typography.headlineMedium.copy(color = textColor),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(cellBackgroundColor)
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center)
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
                                    .border(1.dp, secondaryColor)
                                    .clickable { viewModel.onCellClick(rowIndex, colIndex) }
                                    .background(
                                        if (gameState.winner != null && CheckWinnerLine(gameState.winnerLine, rowIndex, colIndex))
                                            primaryColor.copy(alpha = 0.3f)
                                        else
                                            cellBackgroundColor
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = cell?.toString() ?: "",
                                    style = MaterialTheme.typography.headlineLarge.copy(
                                        fontSize = 36.sp,
                                        color = textColor
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.resetGame() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .background(backgroundColor, shape = MaterialTheme.shapes.medium)
                .height(50.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(
                text = "Reset Game",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = gameState.winner?.let { "$it Winner!!!" } ?: "Current Player: ${gameState.currentPlayer}",
            style = MaterialTheme.typography.bodyLarge.copy(color = textColor),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}