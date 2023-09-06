package com.example.jokeapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.view.isVisible
import com.example.jokeapp.R
import com.example.jokeapp.databinding.FragmentJokeBinding
import com.example.jokeapp.model.Joke
import com.example.jokeapp.presenter.JokePresenter
import com.example.jokeapp.util.Constants.CATEGORY
import com.squareup.picasso.Picasso

class JokeFragment : Fragment() {

    private var _binding: FragmentJokeBinding? = null
    private val binding get() = _binding!!

    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    private lateinit var imageView: ImageView

    private val presenter = JokePresenter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJokeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryName = arguments?.getString(CATEGORY)!!
        activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)?.title = categoryName

        textView = binding.txtJoke
        imageView = binding.imgApi
        progressBar = binding.progressBar

        presenter.findBy(categoryName)

        binding.reloadJoke.setOnClickListener {
            presenter.findBy(categoryName)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun showJoke(joke: Joke){
        textView.text = joke.textJoke

        Picasso.get().load(joke.iconUrl).into(imageView)

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