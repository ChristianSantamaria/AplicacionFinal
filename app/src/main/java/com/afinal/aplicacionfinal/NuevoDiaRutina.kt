package com.afinal.aplicacionfinal

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_nuevo_dia_rutina.*
import java.util.*

class NuevoDiaRutina : AppCompatActivity() {
    var db = DataBaseHandler(this)
    var data = arrayListOf<dtrutina>()
    var desayuno = tipoComidaProductos(ArrayList<Product>(),"Desayuno")
    var almuerzo = tipoComidaProductos(ArrayList<Product>(),"Almuerzo")
    var comida = tipoComidaProductos(ArrayList<Product>(),"Comida")
    var merienda = tipoComidaProductos(ArrayList<Product>(),"Merienda")
    var cena = tipoComidaProductos(ArrayList<Product>(),"Cena")
    var comidas = ArrayList<tipoComidaProductos>()
    var request_code = 1
    var p1 = Product()
    var dia = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_dia_rutina)
        dia = intent.extras.getInt("day")
        labelDia.text = dia.toString()

        var p1 = Product("Zanahorias", 2f, 4, "lacteo", R.drawable.carrots)

        agregarDesayuno.setOnClickListener {
            val intent = Intent(this,  ListadoProductos::class.java)
            startActivityForResult(intent, request_code)
            agregarProducto(p1,"Desayuno" )
        }
        agregarAlmuerzo.setOnClickListener {
            //val intent = Intent(this,  ListadoProductos::class.java)
            //startActivityForResult(intent, request_code)
            agregarProducto(p1,"Almuerzo")
        }
        agregarComida.setOnClickListener {
            //val intent = Intent(this,  ListadoProductos::class.java)
            //startActivityForResult(intent, request_code)
            agregarProducto(p1,"Comida")
        }
        agregarMerienda.setOnClickListener {
            //val intent = Intent(this,  ListadoProductos::class.java)
            //startActivityForResult(intent, request_code)
            agregarProducto(p1,"Merienda")
        }
        agregarCena.setOnClickListener {
            //val intent = Intent(this,  ListadoProductos::class.java)
            //startActivityForResult(intent, request_code)
            agregarProducto(p1,"Cena")
        }

        guardarRutina.setOnClickListener {
            var resultData = Intent(this, Rutina::class.java)
            comidas.add(desayuno)
            comidas.add(almuerzo)
            comidas.add(comida)
            comidas.add(merienda)
            comidas.add(cena)
            data.add(dtrutina(comidas, dia))
            resultData.putExtra("rutinaSeleccionada", data)
            setResult(Activity.RESULT_OK, resultData)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent){
        if ((requestCode == request_code) && (resultCode == RESULT_OK)){
            p1 = data.extras.get("producto") as Product
        }
    }

    fun agregarProducto(producto: Product, tipo: String) {
        //db.insertarCualquierComida(producto,tipo)
        when (tipo) {
            "Desayuno" -> desayuno.productos.add(producto)
            "Almuerzo" -> almuerzo.productos.add(producto)
            "Comida" -> comida.productos.add(producto)
            "Merienda" -> merienda.productos.add(producto)
            "Cena" -> cena.productos.add(producto)
        }
    }
}
