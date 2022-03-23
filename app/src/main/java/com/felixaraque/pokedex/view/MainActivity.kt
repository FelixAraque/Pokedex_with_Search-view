package com.felixaraque.pokedex.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.felixaraque.pokedex.R
import com.felixaraque.pokedex.adapter.CustomAdapter
import com.felixaraque.pokedex.model.Pokedex
import com.felixaraque.pokedex.model.Pokemon
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var pokemons: List<Pokemon>
    private lateinit var adapter: CustomAdapter
    private lateinit var searchView: SearchView
    private lateinit var rvPokemon: RecyclerView
    private lateinit var pokedex: Pokedex

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvPokemon = findViewById<RecyclerView>(R.id.rvPokemon)
        initRV()
        cargarDatos()

    }

    private fun initRV() {
        adapter = CustomAdapter(this, R.layout.rowpokemon)
        rvPokemon.adapter = adapter
        rvPokemon.layoutManager = LinearLayoutManager(this)
    }

    private fun cargarDatos() {
        GlobalScope.launch() {
            val jsontxt = URL("https://pokeapi.co/api/v2/pokemon?limit=898").readText(Charsets.UTF_8)
            launch(Dispatchers.Main) {
                pokedex = Gson().fromJson(jsontxt,Pokedex::class.java)

                pokemons = pokedex.results
                adapter.setPokemons(pokemons)

            }
        }
    }

    fun onClickPokemon(v: View) {
        val pokemonPulsado = v.tag as Pokemon
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("pokemon", pokemonPulsado)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu.findItem(R.id.search)
        searchView = searchItem.actionView as SearchView
        searchView.setQueryHint("Search...")
        searchView.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextChange(query: String): Boolean {
        val original = ArrayList<Pokemon>(pokemons)
        adapter.setPokemons(original.filter { pokemon -> pokemon.name.toLowerCase().contains(query.toLowerCase()) })
        return false
    }

    override fun onQueryTextSubmit(text: String): Boolean {
        return false
    }
}