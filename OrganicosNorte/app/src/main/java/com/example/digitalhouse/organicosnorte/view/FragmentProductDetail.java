package com.example.digitalhouse.organicosnorte.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.digitalhouse.organicosnorte.R;
import com.example.digitalhouse.organicosnorte.controller.ProductoController;
import com.example.digitalhouse.organicosnorte.model.pojo.Producto;


public class FragmentProductDetail extends Fragment {

    public static final String KEY_IDPRODUCTO = "idProducto";
    private Producto producto;
    private ProductModification productModification;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        Bundle bundle = getArguments();

        producto = ProductoController.getProduct(getContext(), bundle.getString(KEY_IDPRODUCTO));

        Button buttonEraseProduct = view.findViewById(R.id.buttonEraseProduct);
        buttonEraseProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productModification = (ProductModification) getContext();
                productModification.deleteProduct(producto);
            }
        });
        return view;
    }

    public interface ProductModification{
        public void deleteProduct(Producto producto);
    }
}
