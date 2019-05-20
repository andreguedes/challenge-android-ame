package br.com.andreguedes.alodjinha.ui.main.home

import android.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.andreguedes.alodjinha.RxImmediateSchedulerRule
import br.com.andreguedes.alodjinha.data.model.Banner
import br.com.andreguedes.alodjinha.data.model.BannerResponse
import br.com.andreguedes.alodjinha.data.model.Category
import br.com.andreguedes.alodjinha.data.model.CategoryResponse
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

class HomePresenterTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var view: HomeContract.View

    @Mock
    private lateinit var service: ALodjinhaService

    @Mock
    private lateinit var api: ALodjinhaAPI

    private lateinit var repository: ALodjinhaRepository
    private lateinit var presenter: HomeContract.Presenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        presenter = HomePresenter(view)
        repository = ALodjinhaRepository(service)
    }

    @Test
    fun shouldReturnBannerListWhenPresenterIsSubscribed() {
        val mockResponse = mock(BannerResponse::class.java)

        val bannerList = arrayListOf(
            Banner(1, "url1", "link1"),
            Banner(2, "url2", "link2"),
            Banner(3, "url3", "link3")
        )

        `when`(mockResponse.bannerList).thenReturn(bannerList).thenThrow(RuntimeException())
        `when`(service.getService()).thenReturn(api)
        `when`(repository.getBanners()).thenReturn(Observable.just(mockResponse))

        presenter.getBanners()
        service.getService()
        repository.getBanners()
        view.setBanners(bannerList)

        verify(view).setBanners(bannerList)
    }

    @Test
    fun shouldReturnCategoryListWhenPresenterIsSubscribed() {
        val mockResponse = mock(CategoryResponse::class.java)

        val categoryList = arrayListOf(
            Category(1, "Categoria 1", "url1"),
            Category(2, "Categoria 2", "url2"),
            Category(3, "Categoria 3", "url3")
        )

        `when`(mockResponse.categoryList).thenReturn(categoryList).thenThrow(RuntimeException())
        `when`(service.getService()).thenReturn(api)
        `when`(repository.getCategories()).thenReturn(Observable.just(mockResponse))

        presenter.getCategories()
        service.getService()
        repository.getCategories()
        view.setCategories(categoryList)

        verify(view).setCategories(categoryList)
    }

}