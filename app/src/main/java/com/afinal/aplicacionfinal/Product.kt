package com.afinal.aplicacionfinal

class Product{

    var id : Int = 0
    var name : String = ""
    var price : Int = 0
    var duration : Int = 0
    var image : Int = 0

    constructor(name:String, price:Int, duration:Int, image:Int){
        this.name = name
        this.price = price
        this.duration = duration
        this.image = image
    }

    constructor(){
    }

}