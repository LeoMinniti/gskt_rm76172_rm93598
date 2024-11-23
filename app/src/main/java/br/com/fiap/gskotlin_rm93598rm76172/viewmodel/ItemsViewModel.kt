package br.com.fiap.gskotlin_rm93598rm76172.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import br.com.fiap.gskotlin_rm93598rm76172.data.ItemDao
import br.com.fiap.gskotlin_rm93598rm76172.data.ItemDatabase
import br.com.fiap.gskotlin_rm93598rm76172.model.EcoDicas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemsViewModel(application: Application) : AndroidViewModel(application) {
    private val itemDao: ItemDao
    val itemsLiveData: LiveData<List<EcoDicas>>
    private val _searchQuery = MutableLiveData<String>()

    init {
        val database = Room.databaseBuilder(
            getApplication(),
            ItemDatabase::class.java,
            "eco_tips_database"
        ).build()

        itemDao = database.itemDao()
        itemsLiveData = itemDao.getAll()
    }

    fun addTip(title: String, description: String, url: String? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            val newItem = EcoDicas(title = title, description = description, url = url)
            itemDao.insert(newItem)
        }
    }

    fun removeItem(item: EcoDicas) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDao.delete(item)
        }
    }

    fun searchItems(query: String) {
        _searchQuery.value = query
    }
}