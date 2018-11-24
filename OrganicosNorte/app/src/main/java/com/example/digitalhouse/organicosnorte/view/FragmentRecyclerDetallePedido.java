package com.example.digitalhouse.organicosnorte.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.digitalhouse.organicosnorte.R;
import com.example.digitalhouse.organicosnorte.adapter.AdapterRecyclerDetallesPedido;
import com.example.digitalhouse.organicosnorte.controller.PedidoController;
import com.example.digitalhouse.organicosnorte.controller.PedidoDetalleController;
import com.example.digitalhouse.organicosnorte.controller.ProductoController;
import com.example.digitalhouse.organicosnorte.model.pojo.PedidoDetalle;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRecyclerDetallePedido extends Fragment {
    public static final String ID_PEDIDO = "idPedido";
    private DeletePedido deletePedido;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_detalle_pedido, container, false);
        final Bundle bundle = getArguments();

        List<PedidoDetalle> detallesPedido = PedidoDetalleController.getProductosDePedido(getContext(),bundle.getInt(ID_PEDIDO));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerDetallesPedidos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        AdapterRecyclerDetallesPedido adapterRecyclerDetallesPedido = new AdapterRecyclerDetallesPedido(detallesPedido);
        recyclerView.setAdapter(adapterRecyclerDetallesPedido);

        ImageButton imageButtonBorrarPedido = view.findViewById(R.id.buttonEliminarDetallesPedidos);
        imageButtonBorrarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePedido = (DeletePedido) getContext();
                deletePedido.deletePedido(bundle.getInt(ID_PEDIDO));
            }
        });


        TextView textView = view.findViewById(R.id.recyclerDetallesPedidosTextViewTotalPrice);
        Double totalPrice = 0.0;
        for (PedidoDetalle pedidoDetalle:detallesPedido) {
            if (PedidoController.getPedido(getContext(), bundle.getInt(ID_PEDIDO)).getCompraVenta()) {
                totalPrice = totalPrice + ProductoController.getProduct(getContext(), pedidoDetalle.getIdProducto()).getPrecioVenta() * pedidoDetalle.getCantidad();
            }else{
                totalPrice = totalPrice + ProductoController.getProduct(getContext(), pedidoDetalle.getIdProducto()).getPrecioCompra() * pedidoDetalle.getCantidad();
            }
        }

        textView.setText("Precio: " + totalPrice.toString());
        return view;
    }

    public interface DeletePedido{
        public void deletePedido(Integer idPedido);
    }
}
