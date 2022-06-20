package com.shatokhin.navigphotoofcats.presentation.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequestBuilder
import com.shatokhin.navigphotoofcats.R
import com.shatokhin.navigphotoofcats.data.room.entity.CatFavorite
import kotlinx.android.synthetic.main.view_holder_favorite_cat.view.*

class AdapterRvFavoriteCats(
    private val clickListenerFavoriteCats: ClickListenerFavoriteCats)
    : ListAdapter<CatFavorite, AdapterRvFavoriteCats.ViewHolderFavoriteCat>(CatsDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFavoriteCat {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_favorite_cat, parent, false)
        return ViewHolderFavoriteCat(view, clickListenerFavoriteCats)
    }

    override fun onBindViewHolder(holder: ViewHolderFavoriteCat, position: Int) {
        holder.bind( getItem(position) )
    }


    class ViewHolderFavoriteCat(
        itemView: View,
        private val clickListenerFavoriteCats: ClickListenerFavoriteCats): RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<SimpleDraweeView>(R.id.ivImageCat)
        val btnDelete = itemView.findViewById<Button>(R.id.btnDelete)

        fun bind(cat: CatFavorite){
            val imageUri = Uri.parse(cat.urlImage)

            val imageRequest= ImageRequestBuilder
                .newBuilderWithSource(imageUri)
                .setProgressiveRenderingEnabled(true)
                .build()

            image.ivImageCat.setImageRequest(imageRequest)

            btnDelete.setOnClickListener {
                clickListenerFavoriteCats.clickFavCat(cat)
            }
        }

    }

    interface ClickListenerFavoriteCats{
        fun clickFavCat(cat: CatFavorite)
    }


}

object CatsDiffCallback : DiffUtil.ItemCallback<CatFavorite>() {
    override fun areItemsTheSame(oldItem: CatFavorite, newItem: CatFavorite): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CatFavorite, newItem: CatFavorite): Boolean {
        return oldItem == newItem
    }
}
