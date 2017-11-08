package com.example.digitalhouse.organicosnorte.controller;

import android.content.Context;

import com.example.digitalhouse.organicosnorte.model.pojo.DBSingleton;
import com.example.digitalhouse.organicosnorte.model.pojo.Producto;

import java.util.List;

/**
 * Created by digitalhouse on 10/27/17.
 */

public class ProductoController {

    public static List<Producto> getAll(Context context){
        return DBSingleton.getInstance(context).productoDao().getAll();
    }

    public static Producto getProduct(Context context, Integer idProducto){
        return DBSingleton.getInstance(context).productoDao().getProduct(idProducto);
    }

    public static void insertProduct(Context context, Producto producto){
        DBSingleton.getInstance(context).productoDao().insertProduct(producto);
    }

    public static void deleteProduct(Context context, Producto producto){
        DBSingleton.getInstance(context).productoDao().deleteProduct(producto);
    }

    public static void modifyProduct(Context context, Producto producto){
        DBSingleton.getInstance(context).productoDao().modifyProduct(producto);
    }
}
