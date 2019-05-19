package br.com.andreguedes.alodjinha.data.source.remote

import br.com.andreguedes.alodjinha.data.model.BannerResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ALodjinhaAPI {

    @GET("/banner")
    fun getBanners(): Observable<BannerResponse>

}