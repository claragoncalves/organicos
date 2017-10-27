package com.example.digitalhouse.organicosnorte.view;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.digitalhouse.organicosnorte.adapter.AdapterRecyclerProductos;
import com.example.digitalhouse.organicosnorte.adapter.AdapterViewPagerProductos;
import com.example.digitalhouse.organicosnorte.R;
import com.example.digitalhouse.organicosnorte.model.pojo.Categoria;

import java.util.ArrayList;
import java.util.List;



public class FragmentViewPagerProductos extends Fragment {

    public static final String KEY_COMPRA  = "Compra";
    public static final String KEY_VENTA  = "Venta";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager_productos, container, false);
        ViewPager viewPager = view.findViewById(R.id.viewPagerListas);
        AdapterViewPagerProductos adapterViewPagerProductos = new AdapterViewPagerProductos(getChildFragmentManager(),loadCategorias());
        viewPager.setAdapter(adapterViewPagerProductos);
        TabLayout tabLayout = view.findViewById(R.id.tabLayoutViewPagerListas);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    private List<Categoria> loadCategorias(){
        List<Categoria> categorias = new ArrayList<>();
        categorias.add(new Categoria(KEY_COMPRA));
        categorias.add(new Categoria(KEY_VENTA));
        return categorias;
    }

}
