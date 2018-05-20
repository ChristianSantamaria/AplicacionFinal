package com.afinal.aplicacionfinal;

import android.app.Activity
import android.app.ListActivity
import android.content.Context
import android.content.Intent
import android.support.annotation.NonNull
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView



import kotlinx.android.synthetic.main.plantilla_elemento_listado_productos.view.*
import android.support.v4.content.ContextCompat.startActivity
import java.sql.SQLOutput

class CustomAdapterListado(var context: Context, var product: ArrayList<Product>) : BaseAdapter() {

    var db = DataBaseHandler(context)

    private class ViewHolder(row: View?){
        var txtName: TextView
        var imageProduct: ImageView

        init {
            this.txtName = row?.findViewById<TextView>(R.id.txtName) as TextView
            this.imageProduct = row?.findViewById<ImageView>(R.id.imageProduct) as ImageView
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View?
        var viewHolder: ViewHolder
        try {
            if(convertView == null){
                var layaut = LayoutInflater.from(context)
                view = layaut.inflate(R.layout.plantilla_elemento_listado_productos, parent,false)
                viewHolder = ViewHolder(view)
                view.tag = viewHolder

                view.setOnClickListener {
                    System.out.println("Pinchaste en el: " + position)

                    var productoAñadirLista = db.leerProducto(position)
                    System.out.println(productoAñadirLista.toString())

                    ListadoProductos().devolverValor(productoAñadirLista)
                }
            }
            else{
                view = convertView
                viewHolder = view.tag as ViewHolder

                view.setOnClickListener {
                    System.out.println("Pinchaste en el: " + position)

                    var productoAñadirLista = db.leerProducto(position)
                    System.out.println(productoAñadirLista.toString())

                    ListadoProductos().devolverValor(productoAñadirLista)
                }
            }

            var product: Product = getItem(position) as Product
            viewHolder.txtName.text = product.name
            viewHolder.imageProduct.setImageResource(product.image)

            return view as View
        }
        catch (e: NullPointerException) {
            var layaut = LayoutInflater.from(context)
            view = layaut.inflate(R.layout.plantilla_elemento_listado_productos, parent,false)
            return view as View
        }

    }

    override fun getItem(position: Int): Any {
        return product.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return product.count()
    }

}
