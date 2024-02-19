package com.example.namelister

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class MyViewModel: ViewModel() {
    val state = mutableStateOf(MyScreenState())

    fun updateText(newText: String) {
        state.value = state.value.copy(text = newText)
    }


    fun updateNamesList(){
        val currentList = state.value.namesList
        currentList.add(state.value.text)
        state.value = state.value.copy(namesList = currentList)
    }
}