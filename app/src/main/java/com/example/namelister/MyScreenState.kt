package com.example.namelister

data class MyScreenState(
    val text: String = "",
    val namesList:MutableList<String> = mutableListOf()
)

