package br.com.andreguedes.alodjinha.ui.product.detail

import br.com.andreguedes.alodjinha.ui.base.BasePresenter
import br.com.andreguedes.alodjinha.ui.base.BaseView

interface ProductDetailContract {

    interface View : BaseView<Presenter> {
        fun reserveProductStatus(status: Boolean)
    }

    interface Presenter : BasePresenter {
        fun reserveProduct(productId: Int?)
    }

}