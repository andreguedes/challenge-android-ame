package br.com.andreguedes.alodjinha.ui.product

import android.view.View
import br.com.andreguedes.alodjinha.ui.category.CategoryAdapter

class NoProductsViewHolder(
    val view: View
) : CategoryAdapter.CategoryProductsViewHolder<Any>(view) {

    override fun onCategoryProductsBindViewHolder(item: Any) {
        super.onBindViewHolder(item)
    }

}