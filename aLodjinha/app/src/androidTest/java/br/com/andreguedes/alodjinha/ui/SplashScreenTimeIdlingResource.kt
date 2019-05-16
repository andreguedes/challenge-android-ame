package br.com.andreguedes.alodjinha.ui

import android.support.test.espresso.IdlingResource

class SplashScreenTimeIdlingResource(private val waitingTime: Long) : IdlingResource {

    private val startTime: Long = System.currentTimeMillis()
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName() = "br.com.andreguedes.alodjinha.ui.SplashScreenTimeIdlingResource"

    override fun isIdleNow(): Boolean {
        val elapsed = System.currentTimeMillis() - startTime
        val idle = elapsed >= waitingTime
        if (idle) {
            resourceCallback?.onTransitionToIdle()
        }
        return idle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.resourceCallback = callback
    }

}