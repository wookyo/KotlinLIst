package com.v1.application.view.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.v1.application.databinding.AdapterMainListHeaderBinding
import com.v1.application.databinding.AdapterMainListItemBinding
import com.v1.application.model.ResponseMainList.MainListItem
import java.lang.ref.WeakReference


class MainListAdapter(callbak: MainListCallback) :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val itemList : MutableList<MainListItem> = ArrayList()
    private val callback : WeakReference<MainListCallback> = WeakReference(callbak)
    private var itemTitle: String? = null

    companion object {
        private const val HEADER = 0
        private const val ITEM = 1
        private const val EMPTY = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER -> HeaderViewHolder(
                AdapterMainListHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            ITEM -> ViewHolder(
                AdapterMainListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> ViewHolder(
                AdapterMainListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                itemTitle?.let {
                    holder.bind(it)
                }?: kotlin.run {
                    holder.bind("NOTHING")
                }
            }
            is ViewHolder ->{
                callback.get()?.let {
                    if(itemList.size >0 && position < itemList.size){
                        holder.bind(itemList[position], it)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (itemList.size == 0) 2 else itemList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemList.size != 0) {
            if (position == 0) HEADER else ITEM
        } else {
            if (position == 0) HEADER else EMPTY
        }
    }

    fun setTitle(title: String){
        this.itemTitle = title

    }
    fun setItemList(newItems: MutableList<MainListItem>?){
        updateList(newItems)
    }

    fun getItemPosition(item: MainListItem):Int{
        return if (itemList.size == 0) 0 else itemList.indexOf(item)
    }

    fun updateList(newItems: List<MainListItem>?) {
        newItems?.let { items ->
            this.itemList.run {
                clear()
                addAll(items)
            }
        }
    }

    inner class ViewHolder(val binding: AdapterMainListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MainListItem?, callback: MainListCallback) {
            with(binding) {
                item?.let { item ->
                    data = item
                    this.callback = callback
                }
            }
        }
    }


    inner class HeaderViewHolder(val binding: AdapterMainListHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String?) {
            with(binding) {
                viewTitle = item
            }
        }
    }
}