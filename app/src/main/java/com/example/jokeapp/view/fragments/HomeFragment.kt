package com.example.jokeapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jokeapp.R
import com.example.jokeapp.databinding.FragmentHomeBinding
import com.example.jokeapp.model.data.CategoryRemoteDataSource
import com.example.jokeapp.model.Category
import com.example.jokeapp.presenter.HomePresenter
import com.example.jokeapp.util.Constants.CATEGORY
import com.example.jokeapp.view.adapter.CategoryItem
import com.xwray.groupie.GroupieAdapter

class HomeFragment: Fragment() {
    private  var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = binding.progressBar

        if(adapter.itemCount ==0){
            presenter.finAllCategories()

        }else{
            hideProgressBar()
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_main)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recyclerView.adapter = adapter

        adapter.setOnItemClickListener { item, view ->
            val bundle = Bundle()
            val categoryName = (item as CategoryItem).category.name
            bundle.putString(CATEGORY, categoryName)
            findNavController().navigate(R.id.action_nav_home_to_jokeFragment, bundle)
        }



    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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

