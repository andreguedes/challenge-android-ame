package br.com.andreguedes.alodjinha.data.source

import br.com.andreguedes.alodjinha.data.model.BannerResponse
import br.com.andreguedes.alodjinha.data.source.remote.ALodjinhaAPI
import br.com.andreguedes.alodjinha.data.source.remote.ALodjinhaService
import io.reactivex.Observable

open class ALodjinhaRepository(
    private var aLodjinhaService: ALodjinhaService
) : ALodjinhaAPI {

    override fun getBanners(): Observable<BannerResponse> {
        return aLodjinhaService.getService().getBanners()
    }

}