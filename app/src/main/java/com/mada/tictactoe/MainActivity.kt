package com.mada.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.mada.presentation.screen.TicTacToeGameScreen
import com.mada.presentation.viewmodel.TicTacToeViewModel
import com.mada.presentation.viewmodel.TicTacToeViewModelFactory
import com.mada.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {
    private val viewModel: TicTacToeViewModel by viewModels { TicTacToeViewModelFactory(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                TicTacToeGameScreen(viewModel = viewModel)
            }
        }
    }
}
