package com.afinal.aplicacionfinal

import android.content.Context
import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.product_item_list.view.*


class CustomAdapter(var context: Context, var product: ArrayList<Product>): BaseAdapter() {

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
        if(convertView == null){
            var layaut = LayoutInflater.from(context)
            view = layaut.inflate(R.layout.product_item_list, parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
            System.out.println("Si")
        }
        else{
            view = convertView
            viewHolder = view.tag as ViewHolder
            System.out.println("No")
            view.btnDelete.setOnClickListener{


            }

        }


        var product: Product = getItem(position) as Product
        viewHolder.txtName.text = product.name
        viewHolder.imageProduct.setImageResource(product.image)

        return view as View
    }

    override fun getItem(position: Int): Any {
        System.out.println(position)
        return product.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return product.count()
    }

}
