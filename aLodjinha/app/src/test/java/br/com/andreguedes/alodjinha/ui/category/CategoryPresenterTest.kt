package br.com.andreguedes.alodjinha.ui.category

import android.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.andreguedes.alodjinha.RxImmediateSchedulerRule
import br.com.andreguedes.alodjinha.data.model.Category
import br.com.andreguedes.alodjinha.data.model.Product
import br.com.andreguedes.alodjinha.data.model.ProductResponse
import br.com.andreguedes.alodjinha.data.source.ALodjinhaRepository
import br.com.andreguedes.alodjinha.data.source.remote.ALodjinhaAPI
import br.com.andreguedes.alodjinha.data.source.remote.ALodjinhaService
import io.reactivex.Observable
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CategoryPresenterTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var view: CategoryContract.View

    @Mock
    private lateinit var service: ALodjinhaService

    @Mock
    private lateinit var api: ALodjinhaAPI

    private lateinit var repository: ALodjinhaRepository
    private lateinit var presenter: CategoryContract.Presenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        presenter = CategoryPresenter(view)
        repository = ALodjinhaRepository(service)
    }

    @Test
    fun shouldReturnProductsFromCategoryListWhenPresenterIsSubscribed() {
        val mockResponse = mock(ProductResponse::class.java)

        val productList = arrayListOf(
            Product(1, "Produto 1", "link1", "Descricao prod 1", 1.9, 1.7,
                Category(1, "Cat 1", "link1")),
            Product(2, "Produto 2", "link2", "Descricao prod 2", 2.9, 2.4,
                Category(1, "Cat 1", "link1")),
            Product(3, "Produto 3", "link3", "Descricao prod 3", 12.8, 11.9,
                Category(1, "Cat 1", "link1"))
        )

        `when`(mockResponse.productList).thenReturn(productList).thenThrow(RuntimeException())
        `when`(service.getService()).thenReturn(api)
        `when`(repository.getProducts(0, 20, 1)).thenReturn(Observable.just(mockResponse))

        presenter.getProductsFromCategory(0)
        service.getService()
        repository.getProducts(0, 20, 1)
        view.setProducts(productList)

        verify(view).setProducts(productList)
    }

}