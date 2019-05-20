package br.com.andreguedes.alodjinha.ui.main.home

import android.util.Log
import br.com.andreguedes.alodjinha.data.source.ALodjinhaRepository
import br.com.andreguedes.alodjinha.data.source.remote.ALodjinhaService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenter(
    private val view: HomeContract.View
) : HomeContract.Presenter {

    private val TAG = HomePresenter::class.java.name

    private val repository = ALodjinhaRepository(ALodjinhaService())

    override fun subscribe() {
        this.getBanners()
        this.getCategories()
    }

    override fun getBanners() {
        addDisposable(repository.getBanners()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.bannerList?.let { banners ->
                    view.setBanners(banners)
                }
            }, {
                Log.e(TAG, it.message)
            })
        )
    }

    override fun getCategories() {
        addDisposable(repository.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.categoryList?.let { categories ->
                    view.setCategories(categories)
                }
            }, {
                Log.e(TAG, it.message)
            })
        )
    }

}