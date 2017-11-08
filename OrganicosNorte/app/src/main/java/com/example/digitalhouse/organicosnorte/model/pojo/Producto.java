package com.example.digitalhouse.organicosnorte.model.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by digitalhouse on 10/26/17.
 */
@Entity(tableName = "productos")
public class Producto {
    @PrimaryKey (autoGenerate = true)
    private Integer id;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "descripcion")
    private String descripcion;
    @ColumnInfo(name = "precio_compra")
    private Double precioCompra;
    @ColumnInfo(name = "precio_venta")
    private Double precioVenta;
    @Ignore
    private Integer quantity;

    public Producto(Integer id, String nombre, String descripcion, Double precioCompra, Double precioVenta) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.quantity = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
