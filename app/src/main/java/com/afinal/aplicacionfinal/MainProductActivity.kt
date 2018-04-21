package com.afinal.aplicacionfinal

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.afinal.aplicacionfinal.R.id.container
import com.afinal.aplicacionfinal.R.id.listView
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.product_item_list.*
import kotlinx.android.synthetic.main.product_main.*


class MainProductActivity() : AppCompatActivity() {

//    var listView = findViewById<ListView>(R.id.listView) as ListView
    var arrProduct: ArrayList<Product> = ArrayList()
    var adapter:CustomAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_main)

        //Llamamos a la base de datos
        val context = this
        var db = DataBaseHandler(context)

        //db.deleteData();

        listView.adapter = CustomAdapter(applicationContext, arrProduct)

        btnNewProduct.setOnClickListener({
            val intent = Intent(baseContext, PopupProductActivity::class.java)
            startActivity(intent)
//            var pPrueba: Product = Product("Queso", 3, 10, "Lacteo", R.drawable.cheeses)
//            arrProduct.add(pPrueba)
//            listView.adapter = CustomAdapter(applicationContext, arrProduct)
//            db.insertProduct(pPrueba)
        })

        btnRead.setOnClickListener({
            var data = db.readData()
            for (i in 0..(data.size - 1)) {
                System.out.println(data.get(i).id.toString() + " " + data.get(i).name + " " + data.get(i).price + " " + data.get(i).duration + " " + data.get(i).type + " " + data.get(i).image + "\n")
                var OldProduct: Product = Product(data.get(i).name , data.get(i).price, data.get(i).duration, data.get(i).type, data.get(i).image);
                arrProduct.add(OldProduct);
                listView.adapter = CustomAdapter(applicationContext, arrProduct)
            }
        })

    }

    fun deleteProduct(index: Int){
        arrProduct.removeAt(index)
        adapter!!.notifyDataSetChanged()

    }
}