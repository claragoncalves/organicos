package com.example.digitalhouse.organicosnorte.model.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

/**
 * Created by Clara on 07/11/2017.
 */
@Entity
public class Pedido {
    @PrimaryKey
    @NonNull
    private String id;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "productos")
    private List<Producto> productos;


    public Pedido(String nombre, List<Producto> productos) {
        this.id = Long.toString(new Date().getTime());
        this.nombre = nombre;
        this.productos = productos;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
