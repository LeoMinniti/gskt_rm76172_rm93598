package br.com.fiap.gskotlin_rm93598rm76172.data

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.fiap.gskotlin_rm93598rm76172.model.EcoDicas



@Database(entities = [EcoDicas::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}