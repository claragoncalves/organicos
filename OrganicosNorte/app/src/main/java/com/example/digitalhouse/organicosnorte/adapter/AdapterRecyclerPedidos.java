package com.example.digitalhouse.organicosnorte.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.digitalhouse.organicosnorte.R;
import com.example.digitalhouse.organicosnorte.model.pojo.Pedido;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by digitalhouse on 11/8/17.
 */

public class AdapterRecyclerPedidos extends RecyclerView.Adapter<AdapterRecyclerPedidos.PedidoViewHolder> {
    private List<Pedido> pedidos;
    private PedidoTapped pedidoTapped;

    public AdapterRecyclerPedidos(Context context, List<Pedido> pedidos) {
        this.pedidos = pedidos;
        pedidoTapped = (PedidoTapped) context;
    }

    @Override
    public PedidoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PedidoViewHolder pedidoViewHolder = new PedidoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_pedido,parent,false));
        return pedidoViewHolder;
    }

    @Override
    public void onBindViewHolder(PedidoViewHolder holder, final int position) {
        final Pedido pedido = pedidos.get(position);
        holder.bindPedido(pedido);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedidoTapped.pedidoSeleccionado(pedido.getId(), pedido.getNombre());
            }
        });
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    public class PedidoViewHolder extends RecyclerView.ViewHolder{
        TextView textViewNombrePedido;
        TextView textViewFechaPedido;
        TextView textViewTipoPedido;

        public PedidoViewHolder(View itemView) {
            super(itemView);
            textViewNombrePedido = itemView.findViewById(R.id.textViewCellNombrePedido);
            textViewFechaPedido = itemView.findViewById(R.id.textViewCellFechaPedido);
            textViewTipoPedido = itemView.findViewById(R.id.textViewCellPedidoCompraVenta);
        }

        public void bindPedido(Pedido pedido){
            if (pedido.getCompraVenta()){
                textViewTipoPedido.setText("VENTA");
                textViewTipoPedido.setTextColor(itemView.getContext().getResources().getColor(R.color.colorPrimaryDark));
            }else {
                textViewTipoPedido.setText("COMPRA");
                textViewTipoPedido.setTextColor(itemView.getContext().getResources().getColor(R.color.colorAccent));
            }
            textViewFechaPedido.setText(pedido.getFecha());
            textViewNombrePedido.setText(pedido.getNombre());

        }
    }

    public interface PedidoTapped{
        public void pedidoSeleccionado(Integer idPedido, String name);
    }
}
