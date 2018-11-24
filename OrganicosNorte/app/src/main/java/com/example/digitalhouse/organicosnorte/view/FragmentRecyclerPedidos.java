package com.example.digitalhouse.organicosnorte.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digitalhouse.organicosnorte.R;
import com.example.digitalhouse.organicosnorte.adapter.AdapterRecyclerPedidos;
import com.example.digitalhouse.organicosnorte.controller.PedidoController;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRecyclerPedidos extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_recycler_pedidos, container, false);
        RecyclerView recyclerViewPedidos = view.findViewById(R.id.recyclerPedidos);
        AdapterRecyclerPedidos adapterRecyclerPedidos = new AdapterRecyclerPedidos(getContext(),PedidoController.getAll(getContext()));
        recyclerViewPedidos.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerViewPedidos.setAdapter(adapterRecyclerPedidos);
        return view;
    }

}
