package com.afinal.aplicacionfinal

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.afinal.aplicacionfinal.R.id.container
import com.afinal.aplicacionfinal.R.id.listView
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.product_item_list.*
import kotlinx.android.synthetic.main.product_main.*


class MainProductActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_main)

        var listView = findViewById<ListView>(R.id.listView) as ListView
        var arrProduct: ArrayList<Product> = ArrayList()
        arrProduct.add(Product("Zanahorias", 2, 4, R.drawable.carrots))
        arrProduct.add(Product("Zanahorias", 2, 4, R.drawable.carrots))
        arrProduct.add(Product("Zanahorias", 2, 4, R.drawable.carrots))
        arrProduct.add(Product("Zanahorias", 2, 4, R.drawable.carrots))
        arrProduct.add(Product("Zanahorias", 2, 4, R.drawable.carrots))
        arrProduct.add(Product("Zanahorias", 2, 4, R.drawable.carrots))
        arrProduct.add(Product("Zanahorias", 2, 4, R.drawable.carrots))
        arrProduct.add(Product("Zanahorias", 2, 4, R.drawable.carrots))
        listView.adapter = CustomAdapter(applicationContext, arrProduct)

        val context = this
        var db = DataBaseHandler(context)

        btnNewProduct.setOnClickListener({
            var pPrueba: Product = Product("Queso", 3, 10, R.drawable.cheeses)
            arrProduct.add(pPrueba)
            listView.adapter = CustomAdapter(applicationContext, arrProduct)
            db.insertProduct(pPrueba)
        })

        btnRead.setOnClickListener({
            var data = db.readData()
            for (i in 0..(data.size - 1)) {
                System.out.println(data.get(i).id.toString() + " " + data.get(i).name + " " + data.get(i).price + " " + data.get(i).duration + " " + data.get(i).image + "\n")
            }
        })

        /**
        btnDelete.setOnClickListener() {

            System.out.println("Entro")

        }
        **/
    }
}