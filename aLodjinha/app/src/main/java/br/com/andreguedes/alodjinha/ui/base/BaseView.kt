package br.com.andreguedes.alodjinha.ui.base

interface BaseView<T> {

    var presenter: T

    fun initUI()
    fun addListeners()

}