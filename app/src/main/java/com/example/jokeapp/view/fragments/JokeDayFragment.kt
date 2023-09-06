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
import androidx.core.view.isVisible
import com.example.jokeapp.R
import com.example.jokeapp.databinding.FragmentJokeBinding
import com.example.jokeapp.databinding.FragmentJokeDayBinding
import com.example.jokeapp.model.Joke
import com.example.jokeapp.presenter.JokeDayPresenter
import com.squareup.picasso.Picasso


class JokeDayFragment : Fragment() {
    private var _binding: FragmentJokeDayBinding? = null
    private val binding get() = _binding!!

    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    private lateinit var imageView: ImageView

    private val presenter: JokeDayPresenter = JokeDayPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding  = FragmentJokeDayBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = binding.progressBar
        textView = binding.txtJoke
        imageView = binding.imgApi


        presenter.findBy()

    }

    override fun onDestroyView() {
        super.onDestroyView()
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