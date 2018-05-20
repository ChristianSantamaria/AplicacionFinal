package com.afinal.aplicacionfinal

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_nuevo_dia_rutina.*

class NuevoDiaRutina : AppCompatActivity() {
    var db = DataBaseHandler(this)
    var comidas = ArrayList<Product>()
    var request_code = 1
    var p1 = Product()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_dia_rutina)


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
            var resultData = Intent(this, Rutina::class.java)
            resultData.putExtra("rutinaSeleccionada", comidas)
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
        when(tipo) {
            "Desayuno"    -> comidas.add(producto)
            "Almuerzo"    -> comidas.add(producto)
            "Comida"      -> comidas.add(producto)
            "Merienda"    -> comidas.add(producto)
            "Cena"        -> comidas.add(producto)
        }
    }

}
