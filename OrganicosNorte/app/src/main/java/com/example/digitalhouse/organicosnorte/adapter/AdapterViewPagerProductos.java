package com.example.digitalhouse.organicosnorte.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.digitalhouse.organicosnorte.model.pojo.Categoria;
import com.example.digitalhouse.organicosnorte.view.FragmentRecyclerProductos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by digitalhouse on 10/26/17.
 */

public class AdapterViewPagerProductos extends FragmentStatePagerAdapter {
    List<FragmentRecyclerProductos> fragmentRecyclerProductosList;
    List<Categoria> categorias;

    public AdapterViewPagerProductos(FragmentManager fm, List<Categoria> categorias) {
        super(fm);
        fragmentRecyclerProductosList = new ArrayList<>();
        this.categorias = categorias;
        for (Categoria categoria:categorias) {
            fragmentRecyclerProductosList.add(FragmentRecyclerProductos.factory(categoria));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentRecyclerProductosList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentRecyclerProductosList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categorias.get(position).getNombre();
    }
}
