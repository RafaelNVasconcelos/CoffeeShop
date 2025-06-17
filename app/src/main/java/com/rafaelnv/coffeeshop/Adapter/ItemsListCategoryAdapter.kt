package com.rafaelnv.coffeeshop.Adapter

import android.content.Context
import android.content.Intent
import android.icu.text.CaseMap.Title
import android.media.Rating
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView
import com.rafaelnv.coffeeshop.Activity.DetailActivity
import com.rafaelnv.coffeeshop.Domain.ItemsModel
import com.rafaelnv.coffeeshop.databinding.ViewholderItemPicLeftBinding
import com.rafaelnv.coffeeshop.databinding.ViewholderItemPicRightBinding

class ItemsListCategoryAdapter(val items:MutableList<ItemsModel>)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        companion object{
            const val TYPE_ITEM1=0
            const val TYPE_ITEM2=1
        }
        lateinit var context:Context
        override fun getItemViewType(position: Int): Int {
            return if(position%2==0) TYPE_ITEM1 else TYPE_ITEM2
    }

    class ViewholderITem1(val binding: ViewholderItemPicRightBinding):
        RecyclerView.ViewHolder(binding.root)

    class ViewholderITem2(val binding: ViewholderItemPicLeftBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return when (viewType){
            TYPE_ITEM1->{
                val binding=ViewholderItemPicRightBinding.inflate(
                    LayoutInflater.from(context),
                    parent, false
                )
                ViewholderITem1(binding)
            }
            TYPE_ITEM2->{
                val binding=ViewholderItemPicLeftBinding.inflate(
                    LayoutInflater.from(context),
                    parent, false
                )
                ViewholderITem2(binding)
            }

            else-> throw IllegalArgumentException("Vizualização Invalida")
        }
    }
    override fun getItemCount(): Int =items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        fun bindCommonData(
            titleTxt: String,
            priceTxt: String,
            rating: Float,
            picUrl: String,
        ) {
            when (holder) {
                is ItemsListCategoryAdapter.ViewholderITem1 -> {
                    holder.binding.titleTxt.text = titleTxt
                    holder.binding.priceTxt.text = priceTxt
                    holder.binding.ratingBar.rating = rating

                    Glide.with(context)
                        .load(picUrl)
                        .into(holder.binding.picMain)

                    holder.itemView.setOnClickListener {
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra("object", items[position])
                        context.startActivity(intent)
                    }
                }

                is ItemsListCategoryAdapter.ViewholderITem2 -> {
                    holder.binding.titleTxt.text = titleTxt
                    holder.binding.priceTxt.text = priceTxt
                    holder.binding.ratingBar.rating = rating

                    Glide.with(context)
                        .load(picUrl)
                        .into(holder.binding.picMain)

                    holder.itemView.setOnClickListener {
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra("onject", items[position])
                        context.startActivity(intent)
                    }
                }
            }
            bindCommonData(
                item.title,
                "R${item.price} Reais",
                item.rating.toFloat(),
                item.picUrl[0]
            )
        }
    }
}