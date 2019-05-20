package br.com.andreguedes.alodjinha.ui.category

import br.com.andreguedes.alodjinha.data.model.Product
import br.com.andreguedes.alodjinha.ui.base.BasePresenter
import br.com.andreguedes.alodjinha.ui.base.BaseView

interface CategoryContract {

    interface View : BaseView<Presenter> {
        fun setProducts(products: List<Product>?)
    }

    interface Presenter : BasePresenter {
        fun getProductsFromCategory(offset: Int)
    }

}