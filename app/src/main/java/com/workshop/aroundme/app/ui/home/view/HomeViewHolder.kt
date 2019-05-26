package com.workshop.aroundme.app.ui.home.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.workshop.aroundme.R
import com.workshop.aroundme.app.ui.home.OnHomePlaceItemClickListener
import com.workshop.aroundme.data.model.PlaceEntity

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameTextView = itemView.findViewById<TextView>(R.id.name)
    private val addressTextView = itemView.findViewById<TextView>(R.id.address)
    private val likesTextView = itemView.findViewById<TextView>(R.id.likes)
    private val likesContainer = itemView.findViewById<View>(R.id.likesContainer)
    private val image = itemView.findViewById<ImageView>(R.id.image)
    private val favorite = itemView.findViewById<ImageView>(R.id.favorite)

    fun bind(
        placeEntity: PlaceEntity,
        onHomePlaceItemClickListener: OnHomePlaceItemClickListener
    ) {
        nameTextView.text = placeEntity.name
        addressTextView.text = placeEntity.address
        likesTextView.text = placeEntity.likes.toString()

        Picasso.get().load(placeEntity.imageUrl).into(image)

        likesContainer.visibility = if (placeEntity.likes != null && placeEntity.likes > 0) {
            View.VISIBLE
        } else {
            View.GONE
        }

        if (placeEntity.isFavorite) {
            favorite.setImageResource(R.drawable.ic_star_on)
        } else {
            favorite.setImageResource(R.drawable.ic_star_off)
        }

        itemView.setOnClickListener {
            onHomePlaceItemClickListener.onPlaceItemCliced(placeEntity)
        }

        favorite.setOnClickListener {
            if (!placeEntity.isFavorite) {
                favorite.setImageResource(R.drawable.ic_star_on)
                placeEntity.isFavorite = true

                onHomePlaceItemClickListener.onItemStarred(placeEntity)
            } else {
                favorite.setImageResource(R.drawable.ic_star_off)
                placeEntity.isFavorite = false

                onHomePlaceItemClickListener.onItemStarred(placeEntity)
            }
        }
    }
}