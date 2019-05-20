package br.com.andreguedes.alodjinha.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Category(
    @field:SerializedName("id") val id: Int? = null,
    @field:SerializedName("descricao") val description: String? = null,
    @field:SerializedName("urlImagem") val urlImage: String? = null
): Serializable

data class CategoryResponse(
    @field:SerializedName("data") val categoryList: List<Category>? = null
)