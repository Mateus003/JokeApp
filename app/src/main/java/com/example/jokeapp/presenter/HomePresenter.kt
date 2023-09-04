package com.example.jokeapp.presenter

import com.example.jokeapp.model.data.CategoryRemoteDataSource
import com.example.jokeapp.model.callback.ListCategoryCallback
import com.example.jokeapp.model.Category
import com.example.jokeapp.view.fragments.HomeFragment


class HomePresenter(private val view: HomeFragment,
                    private val dataSource: CategoryRemoteDataSource
): ListCategoryCallback {
    fun finAllCategories(){
        dataSource. findAllCategories(this)
    }
    //VIEW->PRESENTER
    //PRESENTER->VIEW
    override fun onSuccess(response: List<String>) {
        val cor: Long = 0XFFE2D9C2
        val categories = response.map { category->
            Category(category, cor)
        }

        view.showCategories(categories)
        view.hideProgressBar()

    }
    override fun onError(message: String){
        view.showProgressBar()
        view.showFailure(message)
    }

    override fun onComplete(){
        view.hideProgressBar()
    }


}