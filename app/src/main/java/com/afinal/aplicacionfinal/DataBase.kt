package com.afinal.aplicacionfinal

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.support.annotation.RequiresApi
import android.widget.Toast
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE
import java.time.format.DateTimeFormatter.ofLocalizedDate
import java.time.format.FormatStyle
import java.time.temporal.TemporalQueries.localDate




val DATABASE_NAME ="DataBase"

//Tabla de diseño rutina
val TABLE_RUTINA_MODELO="RutinaModelo"
val COL_ORDEN = "Orden"
val COL_ID_DESAYUNO = "Id_Desayuno"
val COL_ID_ALMUERZO = "Id_Almuerzo"
val COL_ID_COMIDA = "Id_Comida"
val COL_ID_MERIENDA = "Id_Merienda"
val COL_ID_CENA = "Id_Cena"

var COL_ID = "Id"

//Tabla de rutina
val TABLE_RUTINA="Rutina"
val COL_FECHA = "Fecha"
//val COL_ID_DESAYUNO = "Id_Desayuno"
//val COL_ID_ALMUERZO = "Id_Almuerzo"
//val COL_ID_COMIDA = "Id_Comida"
//val COL_ID_MERIENDA = "Id_Merienda"
//val COL_ID_CENA = "Id_Cena"

//Tabla de hora de desayuno
val TABLE_DESAYUNO="Desayuno"

//Tabla de hora de almuerzo
val TABLE_ALMUERZO="Almuerzo"

//Tabla de hora de comer
val TABLE_COMIDA="Comida"

//Tabla de hora de merienda
val TABLE_MERIENDA="Merienda"

//Tabla de hora de cena
val TABLE_CENA="Cena"

//Tabla de productos
val TABLE_PRODUCT="Products"
val COL_ID_PRODUCT = "Id_Product"
val COL_NAME_PRODUCT = "Name"
val COL_PRICE = "Price"
val COL_DURATION = "Duration"
val COL_TYPE = "Type"
val COL_IMAGE = "Image"

//Tabla de carrito
val TABLE_SHOPPINGCART="ShoppingCart"




class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,1){


    override fun onCreate(db: SQLiteDatabase?) {
        //Creacion tabla base de comida
        val crearTablaRutina = "CREATE TABLE IF NOT EXISTS " + TABLE_RUTINA +" (" +
                COL_FECHA +" DATE PRIMARY KEY," +
                COL_ID_DESAYUNO + " INTEGER," +
                COL_ID_ALMUERZO +" INTEGER," +
                COL_ID_COMIDA + " INTEGER," +
                COL_ID_MERIENDA + " INTEGER," +
                COL_ID_CENA + " INTEGER);"
        db?.execSQL(crearTablaRutina)

        //Creacion tabla modelo rutina
        val crearTablaRutinaModelo = "CREATE TABLE IF NOT EXISTS " + TABLE_RUTINA_MODELO +" (" +
                COL_ORDEN +" INTEGER PRIMARY KEY," +
                COL_ID_DESAYUNO + " INTEGER," +
                COL_ID_ALMUERZO +" INTEGER," +
                COL_ID_COMIDA + " INTEGER," +
                COL_ID_MERIENDA + " INTEGER," +
                COL_ID_CENA + " INTEGER);"
        db?.execSQL(crearTablaRutinaModelo)

        //Creacion tabla desayuno
        val crearTablaDesayuno = "CREATE TABLE IF NOT EXISTS " + TABLE_DESAYUNO +" (" +
                COL_ID +" INTEGER," +
                COL_ID_PRODUCT + " INTEGER);"
        db?.execSQL(crearTablaDesayuno)

        //Creacion tabla almuerzo
        val crearTablaAlmuerzo = "CREATE TABLE IF NOT EXISTS " + TABLE_ALMUERZO +" (" +
                COL_ID +" INTEGER," +
                COL_ID_PRODUCT + " INTEGER);"
        db?.execSQL(crearTablaAlmuerzo)

        //Creacion tabla comida
        val crearTablaComida = "CREATE TABLE IF NOT EXISTS " + TABLE_COMIDA +" (" +
                COL_ID +" INTEGER," +
                COL_ID_PRODUCT + " INTEGER);"
        db?.execSQL(crearTablaComida)

        //Creacion tabla merienda
        val crearTablaMerienda = "CREATE TABLE IF NOT EXISTS " + TABLE_MERIENDA +" (" +
                COL_ID +" INTEGER," +
                COL_ID_PRODUCT + " INTEGER);"
        db?.execSQL(crearTablaMerienda)

        //Creacion tabla cena
        val crearTablaCena = "CREATE TABLE IF NOT EXISTS " + TABLE_CENA +" (" +
                COL_ID +" INTEGER," +
                COL_ID_PRODUCT + " INTEGER);"
        db?.execSQL(crearTablaCena)

        //Creacion tabla productos
        val createTableProduct = "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCT +" (" +
                COL_ID_PRODUCT +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME_PRODUCT + " VARCHAR(256)," +
                COL_PRICE +" INTEGER," +
                COL_DURATION + " INTEGER," +
                COL_TYPE + " VARCHAR(256)," +
                COL_IMAGE + " INTEGER);"
        db?.execSQL(createTableProduct)
    }

    override fun onUpgrade(db: SQLiteDatabase?,oldVersion: Int,newVersion: Int) {

    }

    //Insertar cualquier hora de comida (desayuno, almuerzo, comida, merienda, cena)
    fun insertarCualquierComida(Orden : Int, IdProducto : Int, NombreTabla : String){
        val db = this.writableDatabase
        var values = ContentValues()

        values.put(COL_ID, Orden)
        values.put(COL_ID_PRODUCT, IdProducto)

        var result = db.insert(NombreTabla, null, values)
        if(result == -1.toLong())
            Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Insertado " + NombreTabla + " Correctamente",Toast.LENGTH_SHORT).show()
    }

    //Insertar totas las comidas a un dia
    fun insertarRutinaModelo(Orden: Int, DesayunoId : Int, AlmuerzoId : Int, ComidaId : Int, MeriendaId : Int, CenaId: Int){
//        val db = this.writableDatabase
//        var valores = ContentValues()
//
//        valores.put(COL_ORDEN, Orden)
//        valores.put(COL_ID_DESAYUNO, DesayunoId)
//        valores.put(COL_ID_ALMUERZO, AlmuerzoId)
//        valores.put(COL_ID_COMIDA, ComidaId)
//        valores.put(COL_ID_MERIENDA, MeriendaId)
//        valores.put(COL_ID_CENA, CenaId)
//
//        var result = db.insert(TABLE_RUTINA_MODELO, null, valores)
//        if(result == -1.toLong())
//            Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show()
//        else
//            Toast.makeText(context,"Insertado " + TABLE_RUTINA_MODELO + " Correctamente",Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
//Insertar totas las comidas a un dia
    fun insertarRutina(Orden : Long, DesayunoId : Int, AlmuerzoId : Int, ComidaId : Int, MeriendaId : Int, CenaId: Int){
        val db = this.writableDatabase
        var valores = ContentValues()

        val fechaDate = LocalDate.now().plusDays(Orden)
        val fecha = fechaDate.format(ISO_LOCAL_DATE)

        valores.put(COL_FECHA, fecha)
        valores.put(COL_ID_DESAYUNO, DesayunoId)
        valores.put(COL_ID_ALMUERZO, AlmuerzoId)
        valores.put(COL_ID_COMIDA, ComidaId)
        valores.put(COL_ID_MERIENDA, MeriendaId)
        valores.put(COL_ID_CENA, CenaId)

        System.out.println(fecha +" "+ DesayunoId  +" "+ AlmuerzoId +" "+ ComidaId +" "+ MeriendaId +" "+ CenaId)

        var result = db.insert(TABLE_RUTINA, null, valores)
        if(result == -1.toLong())
            Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Insertado " + TABLE_RUTINA + " Correctamente",Toast.LENGTH_SHORT).show()
    }

    //Leer cada una de las comidas
    fun leerComida(NombreTabla: String, IdComida: Int) : MutableList<Product>{
        var listaComida : MutableList<Product> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + NombreTabla + " where " + COL_ID + " = " + IdComida
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                var prod = Product()
                prod = leerProducto(result.getColumnIndex(COL_ID_PRODUCT))
                listaComida.add(prod)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return listaComida
    }

    //Lee la base producto y la carga en la lista
    fun leerRutina() : MutableList<Product>{
        var list : MutableList<Product> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + TABLE_RUTINA
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                result.getColumnIndex(COL_FECHA)
                result.getColumnIndex(COL_ID_DESAYUNO)
                result.getColumnIndex(COL_ID_ALMUERZO)
                result.getColumnIndex(COL_ID_COMIDA)
                result.getColumnIndex(COL_ID_MERIENDA)
                result.getColumnIndex(COL_ID_CENA)

                var prod = Product()
                prod.name = result.getString(result.getColumnIndex(COL_NAME_PRODUCT))
                prod.price = result.getString(result.getColumnIndex(COL_PRICE)).toFloat()
                prod.duration = result.getString(result.getColumnIndex(COL_DURATION)).toInt()
                prod.image = result.getString(result.getColumnIndex(COL_IMAGE)).toInt()
                System.out.println(prod)
                list.add(prod)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }



    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //PRODUCTOS
    fun createTableProduct(db: SQLiteDatabase?){
        val createTable = "CREATE TABLE " + TABLE_PRODUCT +" (" +
                COL_ID_PRODUCT +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME_PRODUCT + " VARCHAR(256)," +
                COL_PRICE +" INTEGER," +
                COL_DURATION + " INTEGER," +
                COL_TYPE + " VARCHAR(256)," +
                COL_IMAGE + " INTEGER);"
        System.out.println("Tabla productos creada")
        db?.execSQL(createTable)
    }

    //Inserta en la base de datos un Producto si todo fue bien manda un toast confirmando
    fun insertProduct(product : Product){
        val db = this.writableDatabase
        var values = ContentValues()

        values.put(COL_NAME_PRODUCT, product.name)
        values.put(COL_PRICE, product.price)
        values.put(COL_DURATION, product.duration)
        values.put(COL_TYPE, product.type)
        values.put(COL_IMAGE, product.image)

        var result = db.insert(TABLE_PRODUCT, null, values)
        if(result == -1.toLong())
            Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Insertado Correctamente",Toast.LENGTH_SHORT).show()
    }

    //Lee la base producto y la carga en la lista
    fun readProduct() : MutableList<Product>{
        var list : MutableList<Product> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + TABLE_PRODUCT
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                var prod = Product()
                prod.name = result.getString(result.getColumnIndex(COL_NAME_PRODUCT))
                prod.price = result.getString(result.getColumnIndex(COL_PRICE)).toFloat()
                prod.duration = result.getString(result.getColumnIndex(COL_DURATION)).toInt()
                prod.image = result.getString(result.getColumnIndex(COL_IMAGE)).toInt()
                System.out.println(prod)
                list.add(prod)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }

    //Lee la base producto y la carga en la lista
    fun leerProducto(Id : Int) : Product{
        var productoVer = Product()

        val db = this.readableDatabase
        val query = "Select * from " + TABLE_PRODUCT + " where " + COL_ID_PRODUCT + " = " + Id
        val result = db.rawQuery(query,null)

        productoVer.id = result.getString(result.getColumnIndex(COL_ID_PRODUCT)).toInt()
        productoVer.name = result.getString(result.getColumnIndex(COL_NAME_PRODUCT))
        productoVer.price = result.getString(result.getColumnIndex(COL_PRICE)).toFloat()
        productoVer.duration = result.getString(result.getColumnIndex(COL_DURATION)).toInt()
        productoVer.type = result.getString(result.getColumnIndex(COL_TYPE))
        productoVer.image = result.getString(result.getColumnIndex(COL_IMAGE)).toInt()
        System.out.println(productoVer)

        result.close()
        db.close()
        return productoVer
    }

    //Borrar la Base de datos
    fun deleteProduct(id: Int){
        val db = this.writableDatabase
        db.delete(TABLE_PRODUCT,COL_ID_PRODUCT + "="+ id,null)
        System.out.println("Base de datos eliminada")

        var arrayProducts : MutableList<Product> = ArrayList()
        arrayProducts = readProduct()

        deleteAllProduct()

        for (i in 0..(arrayProducts.size - 1)) {
            var ProductNew = Product(arrayProducts.get(i).name , arrayProducts.get(i).price, arrayProducts.get(i).duration, arrayProducts.get(i).type, arrayProducts.get(i).image);
            val db = this.writableDatabase
            var values = ContentValues()

            values.put(COL_NAME_PRODUCT, ProductNew.name)
            values.put(COL_PRICE, ProductNew.price)
            values.put(COL_DURATION, ProductNew.duration)
            values.put(COL_TYPE, ProductNew.type)
            values.put(COL_IMAGE, ProductNew.image)

            var result = db.insert(TABLE_PRODUCT, null, values)
        }

        db.close()
    }

    //Borrar la Base de datos
    fun deleteAllProduct(){
        val db = this.writableDatabase
        //db.delete(TABLE_NAME,null,null)

        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT)
        System.out.println("Base de datos eliminada");

        createTableProduct(db)
        db.close()
    }

//    fun updateData() {
//        val db = this.writableDatabase
//        val query = "Select * from " + TABLE_NAME
//        val result = db.rawQuery(query,null)
//        if(result.moveToFirst()){
//            do {
//                var cv = ContentValues()
//                cv.put(COL_PRICE,(result.getInt(result.getColumnIndex(COL_PRICE))+1))
//                db.update(TABLE_NAME,cv,COL_ID + "=? AND " + COL_NAME_PRODUCT + "=?",
//                        arrayOf(result.getString(result.getColumnIndex(COL_ID)),
//                                result.getString(result.getColumnIndex(COL_NAME_PRODUCT))))
//            }while (result.moveToNext())
//        }
//
//        result.close()
//        db.close()
//    }


}