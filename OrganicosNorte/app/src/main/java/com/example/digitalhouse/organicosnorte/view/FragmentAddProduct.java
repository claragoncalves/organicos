package com.example.digitalhouse.organicosnorte.view;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.digitalhouse.organicosnorte.R;
import com.example.digitalhouse.organicosnorte.model.pojo.Producto;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAddProduct extends Fragment {

    private AddProduct addProduct;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_product, container, false);

        Button buttonConfirmAdd = view.findViewById(R.id.buttonConfirmAddProduct);
        buttonConfirmAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProduct = (AddProduct) getActivity();
                addProduct.addProduct(getDataFromView());
            }
        });


        return view;
    }

    public interface AddProduct{
        public void addProduct(Producto producto);
    }

    private Producto getDataFromView(){
        String nombre = "";
        String descripcion = "";
        Double precioCompra = 0.0;
        Double precioVenta = 0.0;
        TextInputEditText textInputEditTextNombre = view.findViewById(R.id.textInputNombre);
        TextInputEditText textInputEditTextDescripcion = view.findViewById(R.id.textInputDescripcion);
        TextInputEditText textInputEditTextPrecioCompra = view.findViewById(R.id.textInputPrecioCompra);
        TextInputEditText textInputEditTextPrecioVenta = view.findViewById(R.id.textInputPrecioVenta);

        if (textInputEditTextNombre.getText().toString().length()>0){
            nombre = textInputEditTextNombre.getText().toString();
        }
        if (textInputEditTextDescripcion.getText().toString().length()>0){
            descripcion = textInputEditTextDescripcion.getText().toString();
        }
        if (textInputEditTextPrecioCompra.getText().toString().length()>0 && textInputEditTextPrecioCompra.getText().toString().contains(".")){
            precioCompra = Double.parseDouble(textInputEditTextPrecioCompra.getText().toString());
        }
        if (textInputEditTextPrecioVenta.getText().toString().length()>0 && textInputEditTextPrecioVenta.getText().toString().contains(".")){
            precioVenta = Double.parseDouble(textInputEditTextPrecioVenta.getText().toString());
        }
        return new Producto(null, nombre,descripcion,precioCompra,precioVenta);
    }
}
