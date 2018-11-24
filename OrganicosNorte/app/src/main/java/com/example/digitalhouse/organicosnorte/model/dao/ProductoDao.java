package com.example.digitalhouse.organicosnorte.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.digitalhouse.organicosnorte.model.pojo.Producto;

import java.util.List;

/**
 * Created by digitalhouse on 10/27/17.
 */
@Dao
public interface ProductoDao {
    @Query("SELECT * FROM productos ORDER BY nombre ASC")
    List<Producto> getAll();

    @Query("SELECT * FROM productos WHERE id = :idProducto")
    Producto getProduct(Integer idProducto);

    @Insert
    void insertProduct(Producto producto);

    @Delete
    void deleteProduct(Producto producto);

    @Update
    void modifyProduct(Producto producto);

}
