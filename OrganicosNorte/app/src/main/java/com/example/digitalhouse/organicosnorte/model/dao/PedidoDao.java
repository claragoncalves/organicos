package com.example.digitalhouse.organicosnorte.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.example.digitalhouse.organicosnorte.model.pojo.Pedido;
import java.util.List;

/**
 * Created by Clara on 07/11/2017.
 */
@Dao
public interface PedidoDao {

    @Query("SELECT * FROM pedidos")
    List<Pedido> getAll();

    @Query("SELECT * FROM pedidos WHERE id = :idPedido")
    Pedido getPedido(Integer idPedido);

    @Insert
    long insertPedido(Pedido pedido);

    @Delete
    void deletePedido(Pedido pedido);

}
