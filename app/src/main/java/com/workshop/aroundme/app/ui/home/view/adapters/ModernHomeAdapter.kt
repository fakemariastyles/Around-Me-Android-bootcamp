package com.workshop.aroundme.app.ui.home.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.app.ui.home.OnHomePlaceItemClickListener
import com.workshop.aroundme.app.ui.home.view.viewholders.CategoriesViewHolder
import com.workshop.aroundme.app.ui.home.view.viewholders.HomeViewHolder
import com.workshop.aroundme.data.model.ParentCategoryEntity
import com.workshop.aroundme.data.model.PlaceEntity

class ModernHomeAdapter(
    private val items: List<PlaceEntity>,
    private val onHomePlaceItemClickListener: OnHomePlaceItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var parentCategories = listOf<ParentCategoryEntity>()
        set(value) {
            field = value
            notifyItemChanged(0)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_TYPE_CATEGORIES_ITEM -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_home_categories_item, parent, false)
                CategoriesViewHolder(view)
            }
            ITEM_TYPE_PLACE_ITEM -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_place_item, parent, false)
                HomeViewHolder(view)
            }
            else -> {
                throw IllegalStateException("Invalid view type")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            ITEM_TYPE_CATEGORIES_ITEM
        } else {
            ITEM_TYPE_PLACE_ITEM
        }
    }

    override fun getItemCount() = items.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM_TYPE_CATEGORIES_ITEM -> {
                (holder as CategoriesViewHolder).bind(parentCategories, onHomePlaceItemClickListener)
            }
            ITEM_TYPE_PLACE_ITEM -> {
                (holder as HomeViewHolder).bind(items[position - 1], onHomePlaceItemClickListener)
            }
        }
    }

    companion object {
        const val ITEM_TYPE_CATEGORIES_ITEM = 0
        const val ITEM_TYPE_PLACE_ITEM = 1
    }

}