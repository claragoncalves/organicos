package com.example.digitalhouse.organicosnorte.view;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.digitalhouse.organicosnorte.adapter.AdapterRecyclerProductos;
import com.example.digitalhouse.organicosnorte.R;
import com.example.digitalhouse.organicosnorte.controller.ProductoController;
import com.example.digitalhouse.organicosnorte.model.pojo.Categoria;
import com.example.digitalhouse.organicosnorte.model.pojo.DBSingleton;
import com.example.digitalhouse.organicosnorte.model.pojo.Pedido;
import com.example.digitalhouse.organicosnorte.model.pojo.PedidoDetalle;
import com.example.digitalhouse.organicosnorte.model.pojo.Producto;

import java.util.ArrayList;
import java.util.List;


public class FragmentRecyclerProductos extends Fragment implements AdapterRecyclerProductos.SumInterface{
    private static final String KEY_CATEGORY_NAME = "nombreCategoria";
    private TextView textViewTotal;
    private AdapterRecyclerProductos adapterRecyclerProductos;
    private PedidoAddListener pedidoAddListener;

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
        adapterRecyclerProductos = new AdapterRecyclerProductos(ProductoController.getAll(getContext()), getContext(),bundle.getString(KEY_CATEGORY_NAME), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterRecyclerProductos);

        Button buttonGuardarPedido = view.findViewById(R.id.buttonGuardarPedido);
        buttonGuardarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });

        return view;
    }


    @Override
    public void quantityTapped(String total) {
        textViewTotal.setText(total);
    }

    public void createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_pedido_layout, null));
        builder.setMessage("Ingrese el nombre del pedido")
                .setTitle("Guardar Pedido")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editText = ((AlertDialog) dialog).findViewById(R.id.editTextDialogPedidoTitle);
                        pedidoAddListener = (PedidoAddListener) getContext();
                        Pedido pedido = new Pedido(null, editText.getText().toString());
                        pedidoAddListener.agregarPedido(pedido, getListaDeProductosAsDetalles());
                    }
                })
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

    }


    public interface PedidoAddListener{
        public void agregarPedido(Pedido pedido, List<PedidoDetalle> pedidoDetalles);
    }


    public List<PedidoDetalle> getListaDeProductosAsDetalles(){
        List<PedidoDetalle> pedidoDetalles = new ArrayList<>();
        for (Producto producto:adapterRecyclerProductos.getListaDeProductos()) {
            if (producto.getQuantity()>0) {
                pedidoDetalles.add(new PedidoDetalle(null, null, producto.getId(), producto.getQuantity()));
            }
        }
        return pedidoDetalles;
    }
}
