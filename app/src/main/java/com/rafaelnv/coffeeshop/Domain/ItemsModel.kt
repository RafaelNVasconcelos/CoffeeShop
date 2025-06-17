package com.rafaelnv.coffeeshop.Domain

import android.content.ClipDescription
import android.icu.text.CaseMap.Title
import java.io.Serializable

data class ItemsModel(
    val title: String="",
    var description:String="",
    var picUrl:ArrayList<String> = ArrayList(),
    var price:Double=0.0,
    var rating:Double=0.0,
    var numberInCart:Int=0,
    var extra:String="",
) : Serializable
