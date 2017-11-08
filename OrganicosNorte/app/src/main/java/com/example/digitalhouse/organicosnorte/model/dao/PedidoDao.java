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

    @Query("SELECT * FROM pedido")
    List<Pedido> getAll();

    @Query("SELECT * FROM pedido WHERE id = :idPedido")
    Pedido getPedido(String idPedido);

    @Insert
    void insertPedido(Pedido pedido);

    @Delete
    void deletePedido(Pedido pedido);

}
