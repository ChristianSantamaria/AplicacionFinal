package com.afinal.aplicacionfinal

class Product{

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


    override fun toString(): String {
        return "Product(id=$id, name='$name', price=$price, duration=$duration, image=$image)"
    }

}