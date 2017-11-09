package com.example.digitalhouse.organicosnorte.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.digitalhouse.organicosnorte.R;
import com.example.digitalhouse.organicosnorte.controller.ProductoController;
import com.example.digitalhouse.organicosnorte.model.pojo.PedidoDetalle;
import com.example.digitalhouse.organicosnorte.model.pojo.Producto;

import java.util.List;

/**
 * Created by digitalhouse on 11/8/17.
 */

public class AdapterRecyclerDetallesPedido extends RecyclerView.Adapter<AdapterRecyclerDetallesPedido.DetallesPedidoViewHolder>{
    List<PedidoDetalle> pedidoDetalles;

    public AdapterRecyclerDetallesPedido(List<PedidoDetalle> pedidoDetalles) {
        this.pedidoDetalles = pedidoDetalles;
    }

    @Override
    public DetallesPedidoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DetallesPedidoViewHolder detallesPedidoViewHolder = new DetallesPedidoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_detalle_pedido, parent, false));
        return detallesPedidoViewHolder;
    }

    @Override
    public void onBindViewHolder(DetallesPedidoViewHolder holder, int position) {
        holder.bindDetallePedido(pedidoDetalles.get(position));
    }

    @Override
    public int getItemCount() {
        return pedidoDetalles.size();
    }

    public class DetallesPedidoViewHolder extends RecyclerView.ViewHolder{
        TextView textViewNombreProducto;
        TextView textViewCantidad;
        TextView textViewDetalleProducto;

        public DetallesPedidoViewHolder(View itemView) {
            super(itemView);
            textViewNombreProducto = itemView.findViewById(R.id.textViewCellDetallePedidoProducto);
            textViewCantidad = itemView.findViewById(R.id.textViewCellDetallePedidoCantidad);
            textViewDetalleProducto = itemView.findViewById(R.id.textViewCellDetallePedidoProductoDescripcion);
        }

        public void bindDetallePedido(PedidoDetalle pedidoDetalle){
            Producto producto = ProductoController.getProduct(itemView.getContext(),pedidoDetalle.getIdProducto());
            if (producto!=null) {
                textViewNombreProducto.setText(producto.getNombre());
                textViewDetalleProducto.setText(producto.getDescripcion());
                textViewCantidad.setText(pedidoDetalle.getCantidad().toString());
            }else {
                textViewNombreProducto.setText("Producto eliminado");
                textViewDetalleProducto.setText("");
                textViewCantidad.setText("");
            }
        }
    }
}
