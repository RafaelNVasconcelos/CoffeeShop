package com.rafaelnv.coffeeshop.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafaelnv.coffeeshop.Adapter.ItemsListCategoryAdapter
import com.rafaelnv.coffeeshop.R
import com.rafaelnv.coffeeshop.ViewModel.MainViewModel
import com.rafaelnv.coffeeshop.databinding.ActivityItemsListBinding

class ItemsListActivity : AppCompatActivity() {
    lateinit var binding:ActivityItemsListBinding
    private val viewModel=MainViewModel()
    private var id:String=""
    private var title:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityItemsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundle()
        initList()

    }

    private fun initList() {
        binding.apply {
            progressBar2.visibility=View.VISIBLE
            viewModel.loadItems(id).observe(this@ItemsListActivity, Observer {
                cartView.layoutManager=
                    LinearLayoutManager(this@ItemsListActivity,
                        LinearLayoutManager.VERTICAL, false)
                cartView.adapter=ItemsListCategoryAdapter(it)
                progressBar2.visibility=View.GONE
            })
            backBtn.setOnClickListener { finish() }
        }
    }

    private fun getBundle() {
        id=intent.getStringExtra("id")!!
        title=intent.getStringExtra("title")!!

        binding.categoryTxt.text=title
    }
}