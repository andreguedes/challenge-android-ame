package br.com.andreguedes.alodjinha.ui.product.detail

import br.com.andreguedes.alodjinha.data.source.ALodjinhaRepository
import br.com.andreguedes.alodjinha.data.source.remote.ALodjinhaService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductDetailPresenter(
    val view: ProductDetailContract.View
) : ProductDetailContract.Presenter {

    private val repository = ALodjinhaRepository(ALodjinhaService())

    override fun subscribe() {}

    override fun reserveProduct(productId: Int?) {
        addDisposable(repository.reserveProduct(productId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.reserveProductStatus(true)
            }, {
                view.reserveProductStatus(false)
            })
        )
    }

}