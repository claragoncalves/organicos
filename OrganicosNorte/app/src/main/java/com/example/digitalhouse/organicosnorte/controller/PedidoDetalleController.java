package com.example.digitalhouse.organicosnorte.controller;

import android.content.Context;

import com.example.digitalhouse.organicosnorte.model.pojo.DBSingleton;
import com.example.digitalhouse.organicosnorte.model.pojo.Pedido;
import com.example.digitalhouse.organicosnorte.model.pojo.PedidoDetalle;

import java.util.List;

/**
 * Created by digitalhouse on 11/8/17.
 */

public class PedidoDetalleController {

    public static List<PedidoDetalle> getProductosDePedido(Context context, Integer idPedido){
        return DBSingleton.getInstance(context).pedidoDetalleDao().getProductosDePedido(idPedido);
    }

    public static void insertDetallesPedido(Context context, List<PedidoDetalle> detallespedido){
        DBSingleton.getInstance(context).pedidoDetalleDao().insertPedido(detallespedido);
    }
}
