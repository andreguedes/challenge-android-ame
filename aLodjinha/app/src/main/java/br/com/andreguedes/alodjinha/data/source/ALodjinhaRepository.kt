package br.com.andreguedes.alodjinha.data.source

import br.com.andreguedes.alodjinha.data.model.BannerResponse
import br.com.andreguedes.alodjinha.data.model.CategoryResponse
import br.com.andreguedes.alodjinha.data.model.ProductResponse
import br.com.andreguedes.alodjinha.data.source.remote.ALodjinhaAPI
import br.com.andreguedes.alodjinha.data.source.remote.ALodjinhaService
import io.reactivex.Observable

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

    private fun getService() = aLodjinhaService.getService()

}