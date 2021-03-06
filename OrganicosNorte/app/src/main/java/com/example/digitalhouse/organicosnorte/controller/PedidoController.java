package com.example.digitalhouse.organicosnorte.controller;

import android.content.Context;
import com.example.digitalhouse.organicosnorte.model.pojo.DBSingleton;
import com.example.digitalhouse.organicosnorte.model.pojo.Pedido;
import java.util.List;

/**
 * Created by Clara on 07/11/2017.
 */

public class PedidoController {

    public static List<Pedido> getAll(Context context){
        return DBSingleton.getInstance(context).pedidoDao().getAll();
    }

    public static Pedido getPedido(Context context, Integer idPedido){
        return DBSingleton.getInstance(context).pedidoDao().getPedido(idPedido);
    }

    public static Pedido getPedidoByName(Context context, String nombrePedido){
        return DBSingleton.getInstance(context).pedidoDao().getPedidoByName(nombrePedido);
    }

    public static long insertPedido(Context context, Pedido pedido){
        return DBSingleton.getInstance(context).pedidoDao().insertPedido(pedido);
    }

    public static void deletePedido(Context context, Pedido pedido){
        DBSingleton.getInstance(context).pedidoDetalleDao().deleteAllDetallesPedidosFromPedido(pedido.getId());
        DBSingleton.getInstance(context).pedidoDao().deletePedido(pedido);
    }
}
