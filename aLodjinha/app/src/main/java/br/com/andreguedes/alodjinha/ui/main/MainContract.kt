package br.com.andreguedes.alodjinha.ui.main

import android.support.v4.app.Fragment
import br.com.andreguedes.alodjinha.ui.base.BasePresenter
import br.com.andreguedes.alodjinha.ui.base.BaseView

interface MainContract {

    interface View : BaseView<Presenter> {
        fun initHomeFragment()
        fun changeFragment(fragment: Fragment)
    }

    interface Presenter : BasePresenter {
        fun initHomeFragment()
    }

}