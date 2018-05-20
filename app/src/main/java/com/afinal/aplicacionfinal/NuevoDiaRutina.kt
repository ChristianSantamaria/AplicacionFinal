package com.afinal.aplicacionfinal

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_nuevo_dia_rutina.*

class NuevoDiaRutina : AppCompatActivity() {
    var db = DataBaseHandler(this)
<<<<<<< HEAD
    var comidas = ArrayList<ArrayList<Product>>()
=======
    var comidas = ArrayList<Product>()
    var request_code = 1
    var p1 = Product()
>>>>>>> c5bd22d64c659e070b9004a3c47618f2ac1d429d

    var aux = ArrayList<Product>()
    var aux2 = ArrayList<Product>()
    var aux3 = ArrayList<Product>()
    var aux4 = ArrayList<Product>()
    var aux5 = ArrayList<Product>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_dia_rutina)

<<<<<<< HEAD
        var p1 = Product(1,"Zanahorias", 2f, 4, "lacteo", R.drawable.carrots)
=======
>>>>>>> c5bd22d64c659e070b9004a3c47618f2ac1d429d

        agregarDesayuno.setOnClickListener {
            val intent = Intent(this,  MainProductActivity::class.java)
            startActivityForResult(intent, request_code)
            agregarProducto(p1,"Desayuno")
        }
        agregarAlmuerzo.setOnClickListener {
            val intent = Intent(this,  MainProductActivity::class.java)
            startActivityForResult(intent, request_code)
            agregarProducto(p1,"Almuerzo")
        }
        agregarComida.setOnClickListener {
            val intent = Intent(this,  MainProductActivity::class.java)
            startActivityForResult(intent, request_code)
            agregarProducto(p1,"Comida")
        }
        agregarMerienda.setOnClickListener {
            val intent = Intent(this,  MainProductActivity::class.java)
            startActivityForResult(intent, request_code)
            agregarProducto(p1,"Merienda")
        }
        agregarCena.setOnClickListener {
            val intent = Intent(this,  MainProductActivity::class.java)
            startActivityForResult(intent, request_code)
            agregarProducto(p1,"Cena")
        }

        guardarRutina.setOnClickListener {
<<<<<<< HEAD
//            var resultData = Intent()
//            resultData.putExtra("rutinaSeleccionada", comidas)
//            setResult(RESULT_OK, resultData)
//            finish()
            db.insertarRutinaModelo(1,1,1,1,1,1)
            db.insertarRutina(1,1,1,1,1,1)
            comidas.add(aux)
            comidas.add(aux2)
            comidas.add(aux3)
            comidas.add(aux4)
            comidas.add(aux5)
=======
            var resultData = Intent(this, Rutina::class.java)
            resultData.putExtra("rutinaSeleccionada", comidas)
            setResult(Activity.RESULT_OK, resultData)
            finish()
>>>>>>> c5bd22d64c659e070b9004a3c47618f2ac1d429d
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent){
        if ((requestCode == request_code) && (resultCode == RESULT_OK)){
            p1 = data.extras.get("producto") as Product
        }
    }

    fun agregarProducto(producto: Product, tipo: String) {
        db.insertarCualquierComida(1,producto.id,tipo)

        when(tipo) {
            "Desayuno"    -> aux.add(producto)
            "Almuerzo"    -> aux2.add(producto)
            "Comida"      -> aux3.add(producto)
            "Merienda"    -> aux4.add(producto)
            "Cena"        -> aux5.add(producto)
        }
    }

}
