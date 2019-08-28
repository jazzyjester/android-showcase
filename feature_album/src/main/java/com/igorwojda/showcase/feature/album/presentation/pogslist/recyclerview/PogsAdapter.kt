package com.igorwojda.showcase.feature.album.presentation.pogslist.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.igorwojda.showcase.library.base.delegate.observer
import com.igorwojda.showcase.library.base.presentation.extension.setOnDebouncedClickListener
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.domain.model.PogDomainModel
import com.pawegio.kandroid.hide
import com.pawegio.kandroid.show
import kotlinx.android.synthetic.main.fragment_album_list_item.view.*

internal class PogsAdapter : RecyclerView.Adapter<PogsAdapter.MyViewHolder>() {

    var pogList: List<PogDomainModel> by observer(listOf()) {
        notifyDataSetChanged()
    }

    private var onDebouncedClickListener: ((pog: PogDomainModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_album_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(pogList[position], position)
    }

    override fun getItemCount(): Int = pogList.size

    fun setOnDebouncedClickListener(listener: (pog: PogDomainModel) -> Unit) {
        this.onDebouncedClickListener = listener
    }

    internal inner class MyViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private var url by observer<String?>(null) {
            itemView.coverErrorImageView.hide()

            if (it == null) {
                setDefaultImage()
            } else {
                itemView.coverImageView.load(it) {
                    crossfade(true)
                    error(R.drawable.ic_image)
                    transformations(RoundedCornersTransformation(10F))
                }
            }
        }

        fun bind(
            pogDomainModel: PogDomainModel,
            position: Int
        ) {
            itemView.setOnDebouncedClickListener { onDebouncedClickListener?.invoke(pogDomainModel) }
//            url = albumDomainModel.getDefaultImageUrl()
            val pogNumber = pogDomainModel.index.toString().padStart(4, '0')
            url = "https://pogim.net/images/pogs/pog_$pogNumber.jpg"

        }

        private fun setDefaultImage() {
            itemView.coverErrorImageView.show()
        }
    }
}
