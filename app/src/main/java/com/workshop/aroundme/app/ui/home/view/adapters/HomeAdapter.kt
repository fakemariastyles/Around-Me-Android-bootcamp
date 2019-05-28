package com.workshop.aroundme.app.ui.home.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.app.ui.home.OnHomePlaceItemClickListener
import com.workshop.aroundme.app.ui.home.view.viewholders.HomeViewHolder
import com.workshop.aroundme.data.model.PlaceEntity

class HomeAdapter(
    private val items: List<PlaceEntity>,
    private val onHomePlaceItemClickListener: OnHomePlaceItemClickListener
) :
    RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_place_item, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(items[position], onHomePlaceItemClickListener)
    }

}