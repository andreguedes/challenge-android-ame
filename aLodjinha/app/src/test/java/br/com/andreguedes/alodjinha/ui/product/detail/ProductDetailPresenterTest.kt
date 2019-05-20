package br.com.andreguedes.alodjinha.ui.product.detail

import android.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.andreguedes.alodjinha.RxImmediateSchedulerRule
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

class ProductDetailPresenterTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var view: ProductDetailContract.View

    @Mock
    private lateinit var service: ALodjinhaService

    @Mock
    private lateinit var api: ALodjinhaAPI

    private lateinit var repository: ALodjinhaRepository
    private lateinit var presenter: ProductDetailContract.Presenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        presenter = ProductDetailPresenter(view)
        repository = ALodjinhaRepository(service)
    }

    @Test
    fun shouldReturnTrueIfProductIsReserved() {
        `when`(service.getService()).thenReturn(api)
        `when`(repository.reserveProduct(1)).thenReturn(Observable.empty())

        presenter.reserveProduct(1)
        service.getService()
        repository.reserveProduct(1)

        verify(view, times(1)).reserveProductStatus(true)
    }

    @Test
    fun shouldReturnFalseIfProductIsNotReserved() {
        `when`(service.getService()).thenReturn(api)
        `when`(repository.reserveProduct(1)).thenReturn(Observable.error(Throwable()))

        presenter.reserveProduct(1)
        service.getService()
        repository.reserveProduct(1)
        view.reserveProductStatus(false)

        verify(view, times(1)).reserveProductStatus(false)
    }

}