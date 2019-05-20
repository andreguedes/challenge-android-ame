package br.com.andreguedes.alodjinha.ui.product

import android.view.View
import br.com.andreguedes.alodjinha.R
import br.com.andreguedes.alodjinha.data.model.Product
import br.com.andreguedes.alodjinha.helper.ImageHelper
import br.com.andreguedes.alodjinha.helper.StringHelper
import br.com.andreguedes.alodjinha.ui.category.CategoryAdapter
import kotlinx.android.synthetic.main.item_product.view.*

class ProductViewHolder(
    val view: View,
    private val listener: (product: Product?) -> Unit
) : CategoryAdapter.CategoryProductsViewHolder<Any>(view) {

    override fun onCategoryProductsBindViewHolder(item: Any) {
        super.onBindViewHolder(item)

        if (item is Product) {
            with(itemView) {
                ImageHelper.loadImage(context, item.urlImage, img_product)

                txt_product_name.text = item.nome
                txt_price_from.text = String.format(
                    context.getString(R.string.price_from),
                    StringHelper.formatCurrencyNew(item.priceFrom)
                )
                txt_price_to.text = String.format(
                    context.getString(R.string.price_to),
                    StringHelper.formatCurrencyNew(item.priceTo)
                )

                setOnClickListener {
                    listener.invoke(item)
                }
            }
        }
    }

}