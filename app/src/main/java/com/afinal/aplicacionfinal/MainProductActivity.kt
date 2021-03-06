package com.afinal.aplicacionfinal

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.product_item_list.*
import kotlinx.android.synthetic.main.product_main.*


class MainProductActivity() : AppCompatActivity() {

    var arrProduct: ArrayList<Product> = ArrayList()
    var adapter: CustomAdapter?=null

    //Llamamos a la base de datos
    val context = this
    var db = DataBaseHandler(context)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_main)

        //db.deleteAllProduct();

        var arrEmpy: ArrayList<Product> = ArrayList()
        listView.adapter = CustomAdapter(applicationContext, arrEmpy)

        var pPrueba: Product = Product("Cordero", 10.5f, 20, "", R.drawable.steaks)
        var pPrueba2: Product = Product("Salmon", 8.5f, 20, "", R.drawable.fish)
        var pPrueba3: Product = Product("Lechuga", 3.5f, 20, "", R.drawable.cabbages)
        var pPrueba4: Product = Product("Leche", 4.5f, 20, "", R.drawable.milk)
        var pPrueba5: Product = Product("Pan", 1f, 20, "", R.drawable.breads)
        db.insertProduct(pPrueba)
        db.insertProduct(pPrueba2)
        db.insertProduct(pPrueba3)
        db.insertProduct(pPrueba4)
        db.insertProduct(pPrueba4)

//        Cargamos todos los productos
        var data = db.readProduct()

        for (i in 0..(data.size - 1)) {
            System.out.println(data.get(i).id.toString() + " " + data.get(i).name + " " + data.get(i).price + " " + data.get(i).duration + " " + data.get(i).type + " " + data.get(i).image + "\n")
            var OldProduct: Product = Product(data.get(i).id, data.get(i).name , data.get(i).price, data.get(i).duration, data.get(i).type, data.get(i).image);
            arrProduct.add(OldProduct);
            listView.adapter = CustomAdapter(applicationContext, arrProduct)
        }

        btnNewProduct.setOnClickListener({
            val intent = Intent(baseContext, PopupProductActivity::class.java)
            startActivity(intent)

        })


        btnDelete?.setOnClickListener({
            var data = db.readProduct()

            for (i in 0..(data.size - 1)) {
                System.out.println(data.get(i).id.toString() + " " + data.get(i).name + " " + data.get(i).price + " " + data.get(i).duration + " " + data.get(i).type + " " + data.get(i).image + "\n")
                var OldProduct: Product = Product(data.get(i).id, data.get(i).name , data.get(i).price, data.get(i).duration, data.get(i).type, data.get(i).image);
                arrProduct.add(OldProduct);
                listView.adapter = CustomAdapter(applicationContext, arrProduct)
            }
        })
    }


    fun createProduct(name: String, price: Float, duration: Int, type: String, icon: Int){
        var pPrueba: Product = Product(name, price, duration, type, icon)

        db.insertProduct(pPrueba)

        arrProduct.add(pPrueba)
        listView.adapter = CustomAdapter(applicationContext, arrProduct)
    }
}