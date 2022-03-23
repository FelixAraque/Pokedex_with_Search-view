package com.felixaraque.pokedex.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.felixaraque.pokedex.R
import com.felixaraque.pokedex.model.Pokemon

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val pokemon = intent.getSerializableExtra("pokemon") as Pokemon
        val wvPokemon = findViewById<WebView>(R.id.wvPokemon)
        wvPokemon.loadUrl("https://www.wikidex.net/wiki/${pokemon.name}")

        wvPokemon.setWebViewClient(WebViewClient())

    }
}
