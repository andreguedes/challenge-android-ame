package br.com.andreguedes.alodjinha.ui.main

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    @Mock
    private lateinit var view: MainContract.View

    private lateinit var presenter: MainContract.Presenter

    @Before
    fun setup() {
        presenter = MainPresenter(view)
    }

    @Test
    fun shouldShowHomeFragmentOnPresenterSubscribe() {
        presenter.subscribe()

        verify(view).initHomeFragment()
    }

}