package com.example.digitalhouse.organicosnorte.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.digitalhouse.organicosnorte.model.pojo.Categoria;
import com.example.digitalhouse.organicosnorte.view.FragmentRecyclerPedidos;
import com.example.digitalhouse.organicosnorte.view.FragmentRecyclerProductos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by digitalhouse on 10/26/17.
 */

public class AdapterViewPagerProductos extends FragmentStatePagerAdapter {
    List<Fragment> fragments;
    List<Categoria> categorias;

    public AdapterViewPagerProductos(FragmentManager fm, List<Categoria> categorias) {
        super(fm);
        fragments = new ArrayList<>();
        this.categorias = categorias;
        for (Categoria categoria:categorias) {
            fragments.add(FragmentRecyclerProductos.factory(categoria));
        }
        categorias.add(new Categoria("Pedidos"));
        fragments.add(new FragmentRecyclerPedidos());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categorias.get(position).getNombre();
    }
}
