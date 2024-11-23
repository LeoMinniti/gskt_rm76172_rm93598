package br.com.fiap.gskotlin_rm93598rm76172

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.gskotlin_rm93598rm76172.viewmodel.ItemsAdapter
import br.com.fiap.gskotlin_rm93598rm76172.viewmodel.ItemsViewModel
import br.com.fiap.gskotlin_rm93598rm76172.viewmodel.ItemsViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : androidx.appcompat.app.AppCompatActivity() {
    private lateinit var viewModel: ItemsViewModel
    private lateinit var searchView: SearchView
    private lateinit var adapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Setar o título como EcoDicas
        supportActionBar?.title = "EcoDicas"

        // Configuração do RecyclerView, ViewModel, etc.
        setupRecyclerView()
        setupSearchView()
        setupViewModel()
        addInitialTips()
        setupFab()

    }

    private fun setupFab() {
        val fab: FloatingActionButton = findViewById(R.id.fabAddTip)
        fab.setOnClickListener {
            showAddTipDialog()
        }
    }

    private fun showAddTipDialog() {
        // Infla o layout personalizado do diálogo
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_tip, null)

        val titleEditText =
            dialogView.findViewById<TextInputEditText>(R.id.editTextTitle)
        val descriptionEditText =
            dialogView.findViewById<TextInputEditText>(R.id.editTextDescription)

        // Configura o diálogo de alerta
        MaterialAlertDialogBuilder(this)
            .setTitle("Adicionar EcoDica")
            .setView(dialogView)
            .setPositiveButton("Adicionar") { _, _ ->
                val title = titleEditText.text.toString()
                val description = descriptionEditText.text.toString()

                if (title.isBlank() || description.isBlank()) {
                    Toast.makeText(this, "Título e descrição são de preenchimento obrigatório", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                // Adiciona a dica usando o ViewModel
                viewModel.addTip(title, description)
                Toast.makeText(this, "EcoDica adicionada com sucesso!", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = ItemsAdapter(
            onItemClicked = { item ->
                showToast(item.description)
            },
            onItemRemoved = { item ->
                viewModel.removeItem(item)
            }
        )
        recyclerView.adapter = adapter
    }

    private fun setupSearchView() {
        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText ?: "")
                return true
            }
        })
    }

    private fun setupViewModel() {
        val viewModelFactory = ItemsViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ItemsViewModel::class.java)

        viewModel.itemsLiveData.observe(this) { items ->
            adapter.updateItems(items)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun addInitialTips() {
        // Adiciona dicas iniciais
        viewModel.addTip(
            "Estude sobre energia limpa",
            "É um sistema de produção de energia que não emite poluentes na atmosfera e tem um impacto mínimo no meio ambiente",
        )
        viewModel.addTip(
            "Defina horários fixos para lavar roupas",
            "O famoso bandeira branca estabele horários cuja energia tem um custo menor quando utilizada dento de um horário pré-determinado pela operadora de energia(como a ENEL)"
        )
    }
}
