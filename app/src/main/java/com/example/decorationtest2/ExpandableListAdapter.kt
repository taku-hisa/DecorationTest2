package com.example.decorationtest2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.decorationtest2.databinding.CellItemBinding
import com.example.decorationtest2.databinding.CellTitleBinding

class ExpandableItemAdapter(
    context: Context,
    private val title: String,
    private val items: List<String>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private var expanded: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == 0) TitleViewHolder(CellTitleBinding.inflate(layoutInflater, parent, false))
        else ItemViewHolder(CellItemBinding.inflate(layoutInflater, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TitleViewHolder) {
            holder.binding.text.text = title
            holder.binding.expandIcon.setImageResource(
                if (expanded) android.R.drawable.presence_online else android.R.drawable.presence_invisible)
            holder.binding.root.setOnClickListener {
                toggleExpand()
            }
        } else if (holder is ItemViewHolder) {
            holder.binding.text.text = items[position - 1]
        }
    }

    override fun getItemCount(): Int = if (expanded) items.size + 1 else 1

    override fun getItemViewType(position: Int): Int = if (position == 0) 0 else 1

    private fun toggleExpand() {
        expanded = !expanded
        notifyItemChanged(0)
        if (expanded) {
            notifyItemRangeInserted(1, items.size)
        } else {
            notifyItemRangeRemoved(1, items.size)
        }
    }

    class TitleViewHolder(val binding: CellTitleBinding) : RecyclerView.ViewHolder(binding.root)

    class ItemViewHolder(val binding: CellItemBinding) : RecyclerView.ViewHolder(binding.root)
}