package com.afinal.aplicacionfinal

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_nuevo_dia_rutina.*

class NuevoDiaRutina : AppCompatActivity() {
    var db = DataBaseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_dia_rutina)
        var p1 = Product("Zanahorias", 2f, 4, "lacteo", R.drawable.carrots)

        agregarDesayuno.setOnClickListener {
            agregarProducto(p1,"Desayuno")
        }
        agregarAlmuerzo.setOnClickListener {
            agregarProducto(p1,"Almuerzo")
        }
        agregarComida.setOnClickListener {
            agregarProducto(p1,"Comida")
        }
        agregarMerienda.setOnClickListener {
            agregarProducto(p1,"Merienda")

        }
        agregarCena.setOnClickListener {
            agregarProducto(p1,"Cena")
        }

        guardarRutina.setOnClickListener {
            var resultData = Intent()
            resultData.putExtra("rutinaSeleccionada", "")
            setResult(RESULT_OK, resultData)
            finish()
        }
    }
    fun agregarProducto(producto: Product, tipo: String) {
        //db.insertarCualquierComida(producto,tipo)

    }

}
