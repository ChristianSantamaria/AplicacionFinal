package com.afinal.aplicacionfinal

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_popup_calendar.*
import android.widget.ArrayAdapter


class PopupCalendarActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_calendar)

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.heightPixels
        window.setLayout((width*.8).toInt(), (height*.8).toInt())

        spinner!!.setOnItemSelectedListener(this)
        val adapter = ArrayAdapter.createFromResource(this, R.array.foods , android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        var arrProduct: ArrayList<Product> = ArrayList()
//        arrProduct.add(Product("Zanahorias", 2, 4, R.drawable.carrots))
//        arrProduct.add(Product("Zanahorias", 2, 4, R.drawable.carrots))
//        arrProduct.add(Product("Zanahorias", 2, 4, R.drawable.carrots))
//        arrProduct.add(Product("Zanahorias", 2, 4, R.drawable.carrots))
//        arrProduct.add(Product("Zanahorias", 2, 4, R.drawable.carrots))
//        arrProduct.add(Product("Zanahorias", 2, 4, R.drawable.carrots))
//        arrProduct.add(Product("Zanahorias", 2, 4, R.drawable.carrots))
//        arrProduct.add(Product("Zanahorias", 2, 4, R.drawable.carrots))
        showfood.adapter = CustomAdapter(applicationContext, arrProduct)
        Log.d("SELECTED:", spinner.getItemAtPosition(position).toString())
    }

}
