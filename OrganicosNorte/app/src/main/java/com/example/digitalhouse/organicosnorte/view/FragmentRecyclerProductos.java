package com.example.digitalhouse.organicosnorte.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.digitalhouse.organicosnorte.adapter.AdapterRecyclerProductos;
import com.example.digitalhouse.organicosnorte.R;
import com.example.digitalhouse.organicosnorte.controller.ProductoController;
import com.example.digitalhouse.organicosnorte.model.pojo.Categoria;
import com.example.digitalhouse.organicosnorte.model.pojo.DBSingleton;


public class FragmentRecyclerProductos extends Fragment implements AdapterRecyclerProductos.SumInterface{
    private static final String KEY_CATEGORY_NAME = "nombreCategoria";
    private TextView textViewTotal;

    public static FragmentRecyclerProductos factory(Categoria categoria){
        FragmentRecyclerProductos fragmentRecyclerProductos = new FragmentRecyclerProductos();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_CATEGORY_NAME, categoria.getNombre());
        fragmentRecyclerProductos.setArguments(bundle);
        return fragmentRecyclerProductos;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_recycler_productos, container, false);

        Bundle bundle = getArguments();
        textViewTotal = view.findViewById(R.id.textViewTotalPrice);
        if (bundle.getString(KEY_CATEGORY_NAME).equals(FragmentViewPagerProductos.KEY_COMPRA)) {
            textViewTotal.setText("Compra: $0.0");
        }else{
            textViewTotal.setText("Venta: $0.0");
        }
        RecyclerView recyclerView = view.findViewById(R.id.recyclerProductos);
        AdapterRecyclerProductos adapterRecyclerProductos = new AdapterRecyclerProductos(ProductoController.getAll(getContext()), getContext(),bundle.getString(KEY_CATEGORY_NAME), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterRecyclerProductos);

        return view;
    }


    @Override
    public void quantityTapped(String total) {
        textViewTotal.setText(total);
    }
}
