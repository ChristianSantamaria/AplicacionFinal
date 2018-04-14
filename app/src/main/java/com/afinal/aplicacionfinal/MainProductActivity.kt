package com.afinal.aplicacionfinal

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.product_main.*


class MainProductActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_main)

        val context = this
        var db = DataBaseHandler(context)

        btnNewProduct.setOnClickListener({
            var pPrueba: Product = Product("Patatas", 3, 10)
            db.insertProduct(pPrueba)
            //db.dropTable()
        })

        btnRead.setOnClickListener({
            var data = db.readData()
            for (i in 0..(data.size - 1)) {
                System.out.println(data.get(i).id.toString() + " " + data.get(i).name + " " + data.get(i).price + " " + data.get(i).duration + "\n")
            }
        })

    }
}