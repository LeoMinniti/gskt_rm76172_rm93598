package br.com.fiap.gskotlin_rm93598rm76172.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.gskotlin_rm93598rm76172.model.EcoDicas


@Dao
interface ItemDao {
    @Query("SELECT * FROM EcoDicas")
    fun getAll(): LiveData< List<EcoDicas> >

    @Query("SELECT * FROM EcoDicas WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
    fun searchItems(searchQuery: String): LiveData< List<EcoDicas> >

    @Insert
    fun insert(item: EcoDicas)

    @Delete
    fun delete(item: EcoDicas)
}