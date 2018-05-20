package com.afinal.aplicacionfinal

import java.io.Serializable
import java.util.ArrayList
data class tipoComidaProductos(val productos: ArrayList<Product>, val tipo: String) : Serializable
data class dtrutina(val comidas: ArrayList<tipoComidaProductos>, val day: Int) : Serializable
