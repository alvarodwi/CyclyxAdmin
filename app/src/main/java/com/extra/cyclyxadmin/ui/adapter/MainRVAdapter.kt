package com.extra.cyclyxadmin.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.extra.cyclyxadmin.databinding.ItemDataBinding
import com.extra.cyclyxadmin.model.ReferenceItem
import com.extra.cyclyxadmin.utils.DELETE_ITEM
import com.extra.cyclyxadmin.utils.EDIT_ITEM

class MainRVAdapter(val clickListener: MainClickListener) :
    ListAdapter<ReferenceItem, MainRVAdapter.MainViewHolder>(MainDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,clickListener)
    }

    class MainViewHolder(private val binding: ItemDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ReferenceItem, clickListener: MainClickListener) {
            binding.item = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MainViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemDataBinding.inflate(layoutInflater, parent, false)
                return MainViewHolder(binding)
            }
        }
    }

    companion object MainDiffUtil : DiffUtil.ItemCallback<ReferenceItem>() {
        override fun areItemsTheSame(oldItem: ReferenceItem, newItem: ReferenceItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ReferenceItem, newItem: ReferenceItem): Boolean {
            return oldItem.uid == newItem.uid
        }
    }

    class MainClickListener(val clickListener: (dataId: String?, action: String) -> Unit) {
        fun onClickResult(data: ReferenceItem) = clickListener(data.uid, EDIT_ITEM)
        fun onDeleteResult(data: ReferenceItem) = clickListener(data.uid, DELETE_ITEM)
    }
}