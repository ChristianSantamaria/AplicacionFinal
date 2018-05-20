package com.afinal.aplicacionfinal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.plantilla_listado_productos.*
import android.database.sqlite.SQLiteDatabase

class ListadoProductos() : AppCompatActivity() {

    var arrProduct: ArrayList<Product> = ArrayList()
    var adapter: CustomAdapterListado?=null

    //Llamamos a la base de datos

    var db = DataBaseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.plantilla_listado_productos)

//        Cargamos todos los productos
        var data = db.readProduct()

        for (i in 0..(data.size - 1)) {
            System.out.println(data.get(i).id.toString() + " " + data.get(i).name + " " + data.get(i).price + " " + data.get(i).duration + " " + data.get(i).type + " " + data.get(i).image + "\n")
            var OldProduct: Product = Product(data.get(i).id, data.get(i).name , data.get(i).price, data.get(i).duration, data.get(i).type, data.get(i).image);
            arrProduct.add(OldProduct);
            listView.adapter = CustomAdapterListado(applicationContext, arrProduct)
        }

    }

    fun devolverValor(productoAñadirLista : Product){

        var resultData = Intent(this, NuevoDiaRutina::class.java)
        resultData.putExtra("producto", productoAñadirLista)

        setResult(Activity.RESULT_OK, resultData)
        finish()
    }
}