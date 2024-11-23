package br.com.fiap.gskotlin_rm93598rm76172.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EcoDicas(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val url: String? = null
)