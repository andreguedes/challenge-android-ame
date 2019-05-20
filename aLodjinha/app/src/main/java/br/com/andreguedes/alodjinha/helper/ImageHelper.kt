package br.com.andreguedes.alodjinha.helper

import android.content.Context
import android.widget.ImageView
import br.com.andreguedes.alodjinha.R
import com.bumptech.glide.Glide

object ImageHelper {

    fun loadImage(context: Context, file: String?, imgView: ImageView) {
        Glide.with(context)
            .load(file)
            .error(R.drawable.logo_menu)
            .into(imgView)
    }

}