package com.example.jokeapp.view.adapter

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.jokeapp.R
import com.example.jokeapp.model.Category
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class CategoryItem(private val category: Category): Item<CategoryItem.CategoryViewHolder>() {
    inner class CategoryViewHolder(view: View): GroupieViewHolder(view){

    }

    override fun createViewHolder(itemView: View): CategoryViewHolder {
        return CategoryViewHolder(itemView)
    }

    override fun bind(viewHolder: CategoryViewHolder, position: Int) {
        viewHolder.itemView.findViewById<TextView>(R.id.text_type).text = category.name.toString()
        viewHolder.itemView.findViewById<LinearLayout>(R.id.container_category)
            .setBackgroundColor(category.backgroundColor.toInt())
    }

    override fun getLayout(): Int {
        return R.layout.item_category
    }
}