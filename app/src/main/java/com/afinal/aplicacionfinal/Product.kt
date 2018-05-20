package com.afinal.aplicacionfinal

import java.io.Serializable

class Product: Serializable{

    var id : Int = 0
    var name : String = ""
    var price : Float = 0F
    var duration : Int = 0
    var type : String = ""
    var image : Int = 0

    constructor(id: Int, name: String, price: Float, duration: Int, type: String, image: Int) {
        this.id = id
        this.name = name
        this.price = price
        this.duration = duration
        this.type = type
        this.image = image
    }

    constructor(name: String, price: Float, duration: Int, type: String, image: Int) {
        this.name = name
        this.price = price
        this.duration = duration
        this.type = type
        this.image = image
    }

    constructor(){}

    fun setid(id: Int){
        this.id = id
    }

    fun setname(name: String){
        this.name = name
    }

    fun setprice(price: Float){
        this.price = price
    }

    fun setduration(duration: Int){
        this.duration = duration
    }

    fun settype(type: String){
        this.type = type
    }

    fun setimage(image: Int){
        this.image = image
    }


    override fun toString(): String {
        return "Product(id=$id, name='$name', price=$price, duration=$duration, image=$image)"
    }

}