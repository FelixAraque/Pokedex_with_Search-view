package com.felixaraque.pokedex.model

import java.io.Serializable

data class Pokedex (var results:List<Pokemon>)
data class Pokemon (var name:String, var url:String): Serializable