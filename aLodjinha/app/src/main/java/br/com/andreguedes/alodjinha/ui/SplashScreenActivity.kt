package br.com.andreguedes.alodjinha.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.andreguedes.alodjinha.ui.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(MainActivity.newIntent(this))
        finish()
    }

}