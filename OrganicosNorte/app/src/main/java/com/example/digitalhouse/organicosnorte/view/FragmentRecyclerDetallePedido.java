package com.example.digitalhouse.organicosnorte.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digitalhouse.organicosnorte.R;
import com.example.digitalhouse.organicosnorte.adapter.AdapterRecyclerDetallesPedido;
import com.example.digitalhouse.organicosnorte.controller.PedidoDetalleController;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRecyclerDetallePedido extends Fragment {
    public static final String ID_PEDIDO = "idPedido";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_detalle_pedido, container, false);
        Bundle bundle = getArguments();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerDetallesPedidos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        AdapterRecyclerDetallesPedido adapterRecyclerDetallesPedido = new AdapterRecyclerDetallesPedido(PedidoDetalleController.getProductosDePedido(getContext(),bundle.getInt(ID_PEDIDO)));
        recyclerView.setAdapter(adapterRecyclerDetallesPedido);
        return view;
    }

}
