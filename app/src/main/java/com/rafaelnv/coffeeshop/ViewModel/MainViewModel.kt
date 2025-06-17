package com.rafaelnv.coffeeshop.ViewModel

import androidx.lifecycle.LiveData
import com.rafaelnv.coffeeshop.Domain.BannerModel
import com.rafaelnv.coffeeshop.Domain.CategoryModel
import com.rafaelnv.coffeeshop.Domain.ItemsModel
import com.rafaelnv.coffeeshop.Repository.MainRepository

class MainViewModel {
    private val repository=MainRepository()

    fun loadBanner():LiveData<MutableList<BannerModel>>{
        return repository.loadBanner()
    }

    fun loadCategory():LiveData<MutableList<CategoryModel>>{
        return repository.loadCategory()
    }

    fun loadPopular():LiveData<MutableList<ItemsModel>>{
        return repository.loadPopular()
    }

    fun loadItems(categoryId:String):LiveData<MutableList<ItemsModel>>{
        return repository.loadItemCategory(categoryId)
    }
}