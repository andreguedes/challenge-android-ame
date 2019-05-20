package br.com.andreguedes.alodjinha.data.source

import br.com.andreguedes.alodjinha.data.model.BannerResponse
import br.com.andreguedes.alodjinha.data.model.CategoryResponse
import br.com.andreguedes.alodjinha.data.model.ProductResponse
import br.com.andreguedes.alodjinha.data.source.remote.ALodjinhaAPI
import br.com.andreguedes.alodjinha.data.source.remote.ALodjinhaService
import io.reactivex.Observable
import retrofit2.Response

open class ALodjinhaRepository(
    private var aLodjinhaService: ALodjinhaService
) : ALodjinhaAPI {

    override fun getBanners(): Observable<BannerResponse> {
        return getService().getBanners()
    }

    override fun getCategories(): Observable<CategoryResponse> {
        return getService().getCategories()
    }

    override fun getProducts(offset: Int, limit: Int, categoriaId: Int?): Observable<ProductResponse> {
        return getService().getProducts(offset, limit, categoriaId)
    }

    override fun getProductsBestSellers(): Observable<ProductResponse> {
        return getService().getProductsBestSellers()
    }

    override fun reserveProduct(produtoId: Int?): Observable<Response<Void>> {
        return getService().reserveProduct(produtoId)
    }

    private fun getService() = aLodjinhaService.getService()

}