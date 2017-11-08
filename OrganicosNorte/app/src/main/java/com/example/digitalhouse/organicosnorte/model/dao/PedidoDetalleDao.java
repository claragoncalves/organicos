package com.example.digitalhouse.organicosnorte.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.digitalhouse.organicosnorte.model.pojo.Pedido;
import com.example.digitalhouse.organicosnorte.model.pojo.PedidoDetalle;

import java.util.List;

/**
 * Created by digitalhouse on 11/8/17.
 */
@Dao
public interface PedidoDetalleDao {

    @Query("SELECT * FROM pedido_detalles WHERE idPedido = :idPedido")
    List<PedidoDetalle> getProductosDePedido(Integer idPedido);

    @Insert
    void insertPedido(List<PedidoDetalle> pedidoDetalles);

    @Delete
    void deletePedido(PedidoDetalle pedidoDetalle);
}
