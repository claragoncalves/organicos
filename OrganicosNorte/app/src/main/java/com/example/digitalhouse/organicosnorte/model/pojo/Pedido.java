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
@Entity(tableName = "pedidos")
public class Pedido {
    @PrimaryKey (autoGenerate = true)
    private Integer id;
    @ColumnInfo(name = "nombre")
    private String nombre;

    public Pedido(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
}
