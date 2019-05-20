package br.com.andreguedes.alodjinha.data.source.remote

import br.com.andreguedes.alodjinha.data.model.BannerResponse
import br.com.andreguedes.alodjinha.data.model.CategoryResponse
import br.com.andreguedes.alodjinha.data.model.ProductResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ALodjinhaAPI {

    @GET("/banner")
    fun getBanners(): Observable<BannerResponse>

    @GET("/categoria")
    fun getCategories(): Observable<CategoryResponse>

    @GET("/produto")
    fun getProducts(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("categoriaId") categoriaId: Int?
    ): Observable<ProductResponse>

    @GET("/produto/maisvendidos")
    fun getProductsBestSellers(): Observable<ProductResponse>

}