package com.example.digitalhouse.organicosnorte.adapter;

import android.content.Context;
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
        holder.bindPedido(pedidos.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedidoTapped.pedidoSeleccionado(pedidos.get(position).getId());
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

        public PedidoViewHolder(View itemView) {
            super(itemView);
            textViewNombrePedido = itemView.findViewById(R.id.textViewCellNombrePedido);
            textViewFechaPedido = itemView.findViewById(R.id.textViewCellFechaPedido);
        }

        public void bindPedido(Pedido pedido){
            textViewNombrePedido.setText(pedido.getNombre());
            textViewFechaPedido.setText(pedido.getFecha().toString());
        }
    }

    public interface PedidoTapped{
        public void pedidoSeleccionado(Integer idPedido);
    }
}
