package com.mada.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TicTacToeViewModelFactory(private val context: Context): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TicTacToeViewModel(context) as T
    }
}