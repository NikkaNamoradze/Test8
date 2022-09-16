package com.example.test8.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.test8.data.model.ShopItemDto
import com.example.test8.databinding.RecyclerItemsBinding
import com.example.test8.extensions.setImage

class ShopAdapter : RecyclerView.Adapter<ShopAdapter.ItemsViewHolder>() {

    private var adapterList = listOf<ShopItemDto>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShopAdapter.ItemsViewHolder {
        return ItemsViewHolder(
            RecyclerItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ShopAdapter.ItemsViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount() = adapterList.size

    inner class ItemsViewHolder(private val binding: RecyclerItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            binding.apply {
                ivImage.setImage(adapterList[adapterPosition].cover)
                tvPrice.text = adapterList[adapterPosition].price
                tvTitle.text = adapterList[adapterPosition].title
            }
            if (adapterList[adapterPosition].liked) {
                binding.vHeart.visibility = View.VISIBLE
            } else {
                binding.vHeart.visibility = View.GONE
            }
        }

    }

    fun setData(data: List<ShopItemDto>){
        val diffUtil = DiffUtils(adapterList, data)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        adapterList = data
        diffResult.dispatchUpdatesTo(this)
    }

    class DiffUtils(
        private val newList: List<ShopItemDto>,
        private val oldList: List<ShopItemDto>
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].title == newList[newItemPosition].title

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

    }

}