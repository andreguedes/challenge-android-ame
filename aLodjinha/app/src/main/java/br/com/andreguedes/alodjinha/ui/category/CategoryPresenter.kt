package br.com.andreguedes.alodjinha.ui.category

import android.util.Log
import br.com.andreguedes.alodjinha.BuildConfig
import br.com.andreguedes.alodjinha.data.source.ALodjinhaRepository
import br.com.andreguedes.alodjinha.data.source.remote.ALodjinhaService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CategoryPresenter(
    private val view: CategoryContract.View,
    private val categoryId: Int? = 0
) : CategoryContract.Presenter {

    private val TAG = CategoryPresenter::class.java.name

    private val repository = ALodjinhaRepository(ALodjinhaService())

    override fun subscribe() {
        this.getProductsFromCategory(0)
    }

    override fun getProductsFromCategory(offset: Int) {
        addDisposable(repository.getProducts(offset, BuildConfig.LIMIT_OFFSET, categoryId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.setProducts(it.productList)
            }, {
                Log.e(TAG, it.message)
            }))
    }

}