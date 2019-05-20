package br.com.andreguedes.alodjinha.ui.category

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import br.com.andreguedes.alodjinha.BuildConfig
import br.com.andreguedes.alodjinha.R
import br.com.andreguedes.alodjinha.data.model.Category
import br.com.andreguedes.alodjinha.data.model.Product
import br.com.andreguedes.alodjinha.ui.base.BaseActivity
import br.com.andreguedes.alodjinha.ui.product.detail.ProductDetailActivity
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : BaseActivity(), CategoryContract.View {

    override lateinit var presenter: CategoryContract.Presenter

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var category: Category

    private var offset = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        initToolbar(R.id.toolbar_normal)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()

        category = intent.getSerializableExtra(EXTRA_CATEGORY) as Category

        initUI()
        addListeners()
    }

    override fun onPause() {
        super.onPause()

        presenter.unsubscribe()
    }

    override fun initUI() {
        title = category.description

        presenter = CategoryPresenter(this, category.id)
        presenter.subscribe()

        layoutManager = LinearLayoutManager(this)

        setupCategories()
    }

    override fun addListeners() {
        val onScrollListener = object : CategoryEndlessRecyclerOnScrollListerner(layoutManager) {
            override fun onLoadMore(current_page: Int) {
                offset += BuildConfig.LIMIT_OFFSET
                presenter.getProductsFromCategory(offset)
            }
        }
        products_list.addOnScrollListener(onScrollListener)
    }

    private fun setupCategories() {
        categoryAdapter = CategoryAdapter {
            startActivity(ProductDetailActivity.newInstance(this, it))
        }

        with(products_list) {
            itemAnimator = DefaultItemAnimator()
            this.layoutManager = layoutManager
            adapter = categoryAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun setProducts(products: List<Product>?) {
        products.let {
            progress_products.visibility = View.GONE
            categoryAdapter.setItens(products, true)
        }
    }

    companion object {

        const val EXTRA_CATEGORY = "br.com.andreguedes.alodjinha.ui.category.CategoryActivity.EXTRA_CATEGORY"

        fun newInstance(context: Context?, category: Category?): Intent {
            val intent = Intent(context, CategoryActivity::class.java)
            intent.putExtra(EXTRA_CATEGORY, category)
            return intent
        }

    }

}