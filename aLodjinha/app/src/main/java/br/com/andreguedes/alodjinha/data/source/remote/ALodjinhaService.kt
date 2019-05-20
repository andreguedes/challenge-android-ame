package br.com.andreguedes.alodjinha.data.source.remote

import br.com.andreguedes.alodjinha.data.source.RetrofitClient

open class ALodjinhaService {

    fun getService(): ALodjinhaAPI {
        return RetrofitClient.getClient()
    }

}