package com.example.digitalhouse.organicosnorte.model.pojo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.digitalhouse.organicosnorte.model.dao.ProductoDao;
import com.example.digitalhouse.organicosnorte.model.pojo.Producto;

/**
 * Created by digitalhouse on 10/27/17.
 */
@Database(version = 1, entities = {Producto.class})
public abstract class DBRoom extends RoomDatabase {
    abstract public ProductoDao productoDao();
}
