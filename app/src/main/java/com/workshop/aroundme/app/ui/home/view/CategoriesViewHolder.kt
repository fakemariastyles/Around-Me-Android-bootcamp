package com.workshop.aroundme.app.ui.home.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.app.ui.home.OnHomePlaceItemClickListener
import com.workshop.aroundme.data.model.ParentCategoryEntity

class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val categoriesTextView = itemView.findViewById<TextView>(R.id.categories)
    fun bind(
        parentCategories: List<ParentCategoryEntity>,
        onHomePlaceItemClickListener: OnHomePlaceItemClickListener
    ) {
        categoriesTextView.text = parentCategories.joinToString("\n") { it.name.toString() }
    }
}