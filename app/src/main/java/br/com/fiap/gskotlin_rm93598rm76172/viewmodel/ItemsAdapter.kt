package br.com.fiap.gskotlin_rm93598rm76172.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.gskotlin_rm93598rm76172.R
import br.com.fiap.gskotlin_rm93598rm76172.model.EcoDicas

class ItemsAdapter(
    private val onItemClicked: (EcoDicas) -> Unit,
    private val onItemRemoved: (EcoDicas) -> Unit
) : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    private var items = listOf<EcoDicas>()
    private var filteredItems = listOf<EcoDicas>()

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView = view.findViewById<TextView>(R.id.textViewTitle)
        val descriptionView = view.findViewById<TextView>(R.id.textViewDescription)
        val button = view.findViewById<ImageButton>(R.id.imageButton)

        fun bind(item: EcoDicas) {
            titleView.text = item.title
            descriptionView.text = item.description

            itemView.setOnClickListener {
                onItemClicked(item)
            }

            button.setOnClickListener {
                onItemRemoved(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.eco_tip_item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = filteredItems.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(filteredItems[position])
    }

    fun updateItems(newItems: List<EcoDicas>) {
        items = newItems
        filteredItems = newItems
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredItems = if (query.isEmpty()) {
            items
        } else {
            items.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }
}
