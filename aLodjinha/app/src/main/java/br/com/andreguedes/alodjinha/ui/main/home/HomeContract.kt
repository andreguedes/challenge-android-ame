package br.com.andreguedes.alodjinha.ui.main.home

import br.com.andreguedes.alodjinha.data.model.Banner
import br.com.andreguedes.alodjinha.ui.base.BasePresenter
import br.com.andreguedes.alodjinha.ui.base.BaseView

interface HomeContract {

    interface View : BaseView<Presenter> {
        fun setBanners(banners: List<Banner>)
    }

    interface Presenter : BasePresenter {
        fun getBanners()
    }

}