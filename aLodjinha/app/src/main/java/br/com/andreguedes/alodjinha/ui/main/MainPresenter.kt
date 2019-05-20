package br.com.andreguedes.alodjinha.ui.main

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {

    override fun initHomeFragment() {
        view.initHomeFragment()
    }

    override fun subscribe() {
        this.initHomeFragment()
    }

}