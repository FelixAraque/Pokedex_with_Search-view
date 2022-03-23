package com.felixaraque.pokedex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.felixaraque.pokedex.R
import com.felixaraque.pokedex.model.Pokemon
import com.squareup.picasso.Picasso

class CustomAdapter(val context: Context,
                    val layout: Int
                    ) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var dataList: List<Pokemon> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setPokemons(pokemons: List<Pokemon>) {
        this.dataList = pokemons
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Pokemon){

            val ivPokemon = itemView.findViewById(R.id.ivPokemon) as ImageView
            val tvNombre = itemView.findViewById(R.id.tvNombre) as TextView
            val tvNumero = itemView.findViewById(R.id.tvNumero) as TextView

            val urlPartida = dataItem.url.split("/")
            var nPokemon = urlPartida[urlPartida.size-2]

            if (nPokemon.toInt() < 10) {
                nPokemon = "00${nPokemon}"
            } else if (nPokemon.toInt() < 100) {
                nPokemon = "0${nPokemon}"
            }

            var nombrepokemon = StringBuilder(dataItem.name)
            nombrepokemon = nombrepokemon.deleteCharAt(0)

            tvNombre.text = "${dataItem.name[0].uppercase()}${nombrepokemon}"
            tvNumero.text = "#${nPokemon}"

            Picasso.get().load("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/${nPokemon}.png").into(ivPokemon)

            itemView.tag = dataItem

        }

    }
}
