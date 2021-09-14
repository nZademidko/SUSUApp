package com.susu.susuapp.models.people

data class People(
    val avatarUrl: String,
    val name: String,
    val position: String = "Член СС",
    val strokeColor: String = "#d2d2d2",
    val nameColor: String = "#ff6e86"
)