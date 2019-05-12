package com.tayfuncesur.assistedinjection.model

data class Civilization(
    val army_type: String,
    val civilization_bonus: List<String>,
    val expansion: String,
    val id: Int,
    val name: String,
    val team_bonus: String,
    val unique_tech: List<String>,
    val unique_unit: List<String>
)