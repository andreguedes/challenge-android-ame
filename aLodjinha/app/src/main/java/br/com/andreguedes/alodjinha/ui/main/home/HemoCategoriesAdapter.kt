package br.com.andreguedes.alodjinha.ui.main.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.andreguedes.alodjinha.R
import br.com.andreguedes.alodjinha.data.model.Category
import br.com.andreguedes.alodjinha.helper.ImageHelper
import kotlinx.android.synthetic.main.item_category.view.*

class HomeCategoriesAdapter(
    private val listener: (category: Category?) -> Unit
) : RecyclerView.Adapter<HomeCategoriesAdapter.HomeCategoriesViewHolder>() {

    private var categoryList = arrayListOf<Category>()

    fun setCategories(categoryList: List<Category>) {
        this.categoryList.clear()
        this.categoryList.addAll(categoryList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return HomeCategoriesViewHolder(view)
    }

    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: HomeCategoriesViewHolder, position: Int) {
        holder.bindItem(categoryList[position])
    }

    inner class HomeCategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(category: Category) {
            with(itemView) {
                ImageHelper.loadImage(this.context, category.urlImagem, img_category)
                txt_category_name.text = category.descricao
                setOnClickListener {
                    listener.invoke(category)
                }
            }
        }

    }

}