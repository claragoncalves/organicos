package com.example.digitalhouse.organicosnorte.model.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by digitalhouse on 11/8/17.
 */
@Entity(tableName = "pedido_detalles")
@ForeignKey(entity = Pedido.class, parentColumns ="id", childColumns = "idPedido")
public class PedidoDetalle {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @ColumnInfo(name = "idPedido")
    private Integer idPedido;
    @ColumnInfo(name = "idProducto")
    private Integer idProducto;
    @ColumnInfo(name = "cantidad")
    private Integer cantidad;

    public PedidoDetalle(Integer id, Integer idPedido, Integer idProducto, Integer cantidad) {
        this.id = id;
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
