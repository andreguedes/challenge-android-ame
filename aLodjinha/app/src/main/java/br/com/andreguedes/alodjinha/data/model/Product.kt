package br.com.andreguedes.alodjinha.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(
    @field:SerializedName("id") val id: Int? = null,
    @field:SerializedName("nome") val name: String? = null,
    @field:SerializedName("urlImagem") val urlImage: String? = null,
    @field:SerializedName("descricao") val description: String? = null,
    @field:SerializedName("precoDe") val priceFrom: Double? = null,
    @field:SerializedName("precoPor") val priceTo: Double? = null,
    @field:SerializedName("categoria") val category: Category? = null
): Serializable

data class ProductResponse(
    @field:SerializedName("data") val productList: List<Product>? = null
)