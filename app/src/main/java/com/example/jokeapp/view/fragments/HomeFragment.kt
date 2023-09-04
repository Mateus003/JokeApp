package com.example.jokeapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jokeapp.R
import com.example.jokeapp.model.data.CategoryRemoteDataSource
import com.example.jokeapp.model.Category
import com.example.jokeapp.presenter.HomePresenter
import com.example.jokeapp.view.adapter.CategoryItem
import com.xwray.groupie.GroupieAdapter

class HomeFragment: Fragment() {

    private lateinit var presenter: HomePresenter

    private lateinit var progressBar: ProgressBar

    private val adapter =GroupieAdapter()


    private val dataSource = CategoryRemoteDataSource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = HomePresenter(this, dataSource)



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.finAllCategories()

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_main)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recyclerView.adapter = adapter

        progressBar = view.findViewById(R.id.progress_bar)

        val dataSource = CategoryRemoteDataSource()

    }

     fun showCategories(response: List<Category>){
         val categories = response.map {category ->  
             CategoryItem(category)
         }
        adapter.addAll(categories)
        adapter.notifyDataSetChanged()

    }

    fun showFailure(message:String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun showProgressBar(){
        progressBar.isVisible = true
    }

    fun hideProgressBar(){
        progressBar.isVisible = false
    }


}

